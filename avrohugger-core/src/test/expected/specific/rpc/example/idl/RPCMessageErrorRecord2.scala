/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package example.idl

import scala.annotation.switch

case class RPCMessageErrorRecord2(var value: Any = null, var cause: java.lang.Throwable = null, var id: String) extends org.apache.avro.specific.SpecificExceptionBase(value, cause) with org.apache.avro.specific.SpecificRecord {
  def this() = this(null, null, "")
  def get(field$: Int): AnyRef = {
    (field$: @switch) match {
      case 2 => {
        id
      }.asInstanceOf[AnyRef]
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
  }
  def put(field$: Int, value: Any): Unit = {
    (field$: @switch) match {
      case 2 => this.id = {
        value.toString
      }.asInstanceOf[String]
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
    ()
  }
  def getSchema: org.apache.avro.Schema = RPCMessageErrorRecord2.SCHEMA$
  def writeExternal(objectOutput: java.io.ObjectOutput): Unit = {
    RPCMessageErrorRecord2.WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(objectOutput))
  }
  def readExternal(objectInput: java.io.ObjectInput): Unit = {
    RPCMessageErrorRecord2.READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(objectInput))
  }
}

object RPCMessageErrorRecord2 {
  val SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"error\",\"name\":\"RPCMessageErrorRecord2\",\"namespace\":\"example.idl\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"}]}")
  val MODEL$ = new org.apache.avro.specific.SpecificData()
  val WRITER$ = MODEL$.createDatumWriter(SCHEMA$).asInstanceOf[org.apache.avro.io.DatumWriter[RPCMessageErrorRecord2]]
  val READER$ = MODEL$.createDatumReader(SCHEMA$).asInstanceOf[org.apache.avro.io.DatumReader[RPCMessageErrorRecord2]]
}