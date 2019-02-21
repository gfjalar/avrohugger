package avrohugger
package format
package specific
package trees

import format.specific.methods.{_}
import avrohugger.generators.ScalaDocGenerator
import avrohugger.matchers.{DefaultParamMatcher, DefaultValueMatcher, TypeMatcher}
import avrohugger.stores.ClassStore
import treehugger.forest._
import definitions._
import org.apache.avro.Schema
import treehuggerDSL._

import scala.collection.JavaConverters._

object SpecificCaseClassTree {

  def toCaseClassDef(
    classStore: ClassStore,
    namespace: Option[String],
    schema: Schema,
    typeMatcher: TypeMatcher,
    maybeBaseTrait: Option[String],
    maybeFlags: Option[List[Long]],
    restrictedFields: Boolean) = {

    val classSymbol = RootClass.newClass(schema.getName)
    val avroFields = schema.getFields.asScala.toList

    val shouldGenerateSimpleClass = restrictedFields && avroFields.size > 22

    // generate list of constructor parameters
    val avroParams: List[ValDef] = avroFields.map { f =>
      val fieldName = f.name
      val fieldType = typeMatcher.toScalaType(classStore, namespace, f.schema)
      val defaultValue = DefaultValueMatcher.getDefaultValue(
        classStore,
        namespace,
        f,
        typeMatcher)
      VAR(fieldName, fieldType) := defaultValue
    }
    val errorParams: List[ValDef] = if (schema.isError()) {
      (VAR("value", AnyClass) := NULL) :: (VAR("cause", ThrowableClass) := NULL) :: Nil
    } else {
      Nil
    }

    // extension
    val errorBaseClass = RootClass.newClass("org.apache.avro.specific.SpecificExceptionBase") APPLY (REF("value"), REF("cause"))
    val baseClass = if (schema.isError) {
      RootClass.newClass("org.apache.avro.specific.SpecificRecord")
    } else {
      RootClass.newClass("org.apache.avro.specific.SpecificRecordBase")
    }

    // no-arg constructor: make arbitrary default if none is provided
    val defaultAvroParams: List[Tree] = avroFields.zip(avroParams).map(f => {
      val (avroField, defaultValue) = (f._1, f._2.rhs)
      if (defaultValue == EmptyTree)
        DefaultParamMatcher.asDefaultParam(classStore, avroField.schema, typeMatcher)
      else
        defaultValue
    })

    val params: List[ValDef] = errorParams ::: avroParams
    val defaultParams: List[Tree] = errorParams.map(_.rhs) ::: defaultAvroParams

    val defThis = DEFTHIS.withParams(PARAM("")).tree := {
      THIS APPLY(defaultParams:_*)
    }

    // methods - first add an index the the schema's fields
    val indexedFields = avroFields.zipWithIndex.map(p => {
      val avroField = p._1
      val index = p._2
      IndexedField(avroField, errorParams.size + index)
    })
    val defGetSchema = GetSchemaGenerator(classSymbol).toDef
    val defGet = GetGenerator.toDef(indexedFields, classSymbol, typeMatcher)
    val defPut = PutGenerator.toDef(
      classStore,
      namespace,
      indexedFields,
      typeMatcher,
      classSymbol)
    val defWriteExternal = WriteExternalGenerator(classSymbol).toDef
    val defReadExternal = ReadExternalGenerator(classSymbol).toDef

    // define the class def with the members previously defined
    // There could be base traits, flags, or both, and could have no fields
    val classDef = {
      val baseDef = if (shouldGenerateSimpleClass) { CLASSDEF(classSymbol) } else { CASECLASSDEF(classSymbol) }

      val defWithParents = if (schema.isError) {
        baseDef.withParents(errorBaseClass).withParents(baseClass)
      } else {
        baseDef.withParents(baseClass)
      }

      val defWithTrait = if (maybeBaseTrait.nonEmpty) {
        defWithParents.withParents(maybeBaseTrait.get)
      } else if (shouldGenerateSimpleClass) {
        defWithParents.withParents("Serializable")
      } else {
        defWithParents
      }

      val defWithFlags = defWithTrait.withFlags(maybeFlags.getOrElse(List.empty[Long]): _*)
      val defWithParams = if (params.nonEmpty) defWithFlags.withParams(params) else defWithFlags.withParams(PARAM(""))

      defWithParams
    }

    val blockElements = List(defThis).filter(_ => params.nonEmpty) :::
      List(defGet, defPut, defGetSchema) :::
      List(defWriteExternal, defReadExternal).filter(_ => schema.isError)

    val caseClassTree = classDef := BLOCK(blockElements)

    val treeWithScalaDoc = ScalaDocGenerator.docToScalaDoc(
      Left(schema),
      caseClassTree)

    treeWithScalaDoc

  }

}
