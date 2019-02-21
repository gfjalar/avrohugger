package avrohugger
package format
package specific
package methods

import treehugger.forest._
import definitions._
import treehuggerDSL._

case class ReadExternalGenerator(companionObjectName: Symbol) {
  def toDef = {
    val ObjectInputClass = RootClass.newClass("java.io.ObjectInput")
    val SpecificDataClass = RootClass.newClass("org.apache.avro.specific.SpecificData")
    DEF("readExternal", UnitClass) withParams(PARAM("objectInput", ObjectInputClass)) := BLOCK(
      REF(companionObjectName) DOT "READER$" DOT "read" APPLY (THIS, (SpecificDataClass DOT "getDecoder") APPLY (REF("objectInput")))
    )
  }
}
