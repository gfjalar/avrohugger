package avrohugger
package format
package specific
package methods

import treehugger.forest._
import definitions._
import treehuggerDSL._

case class WriteExternalGenerator(companionObjectName: Symbol) {
  def toDef = {
    val ObjectOutputClass = RootClass.newClass("java.io.ObjectOutput")
    val SpecificDataClass = RootClass.newClass("org.apache.avro.specific.SpecificData")
    DEF("writeExternal", UnitClass) withParams(PARAM("objectOutput", ObjectOutputClass)) := BLOCK(
      REF(companionObjectName) DOT "WRITER$" DOT "write" APPLY (THIS, (SpecificDataClass DOT "getEncoder") APPLY (REF("objectOutput")))
    )
  }
}
