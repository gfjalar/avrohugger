/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package example.idl

import scala.annotation.switch

case class RPCMessageErrorRecord1(var value: Any = null, var cause: java.lang.Throwable = null) extends org.apache.avro.specific.SpecificExceptionBase(value, cause) with org.apache.avro.specific.SpecificRecord {
  def this() = this(null, null)
  def get(field$: Int): AnyRef = {
    (field$: @switch) match {
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
  }
  def put(field$: Int, value: Any): Unit = {
    (field$: @switch) match {
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
    ()
  }
  def getSchema: org.apache.avro.Schema = RPCMessageErrorRecord1.SCHEMA$
  def writeExternal(objectOutput: java.io.ObjectOutput): Unit = {
    RPCMessageErrorRecord1.WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(objectOutput))
  }
  def readExternal(objectInput: java.io.ObjectInput): Unit = {
    RPCMessageErrorRecord1.READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(objectInput))
  }
}

object RPCMessageErrorRecord1 {
  val SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"error\",\"name\":\"RPCMessageErrorRecord1\",\"namespace\":\"example.idl\",\"fields\":[]}")
  val MODEL$ = new org.apache.avro.specific.SpecificData()
  val WRITER$ = MODEL$.createDatumWriter(SCHEMA$).asInstanceOf[org.apache.avro.io.DatumWriter[RPCMessageErrorRecord1]]
  val READER$ = MODEL$.createDatumReader(SCHEMA$).asInstanceOf[org.apache.avro.io.DatumReader[RPCMessageErrorRecord1]]
}