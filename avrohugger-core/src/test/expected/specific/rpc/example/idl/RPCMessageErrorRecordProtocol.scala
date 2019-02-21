/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package example.idl

@SuppressWarnings(Array("all"))
@org.apache.avro.specific.AvroGenerated
trait RPCMessageErrorRecordProtocol {
  def doThrow(): Boolean
  @SuppressWarnings(Array("all"))
  trait Callback extends RPCMessageErrorRecordProtocol {
    final val PROTOCOL: org.apache.avro.Protocol = example.idl.RPCMessageErrorRecordProtocol.PROTOCOL
    /** @throws java.io.IOException The async call could not be completed. */
    def doThrow(callback: org.apache.avro.ipc.Callback[Boolean]): Unit
  }
}

object RPCMessageErrorRecordProtocol {
  final val PROTOCOL: org.apache.avro.Protocol = org.apache.avro.Protocol.parse("{\"protocol\":\"RPCMessageErrorRecordProtocol\",\"namespace\":\"example.idl\",\"types\":[{\"type\":\"error\",\"name\":\"RPCMessageErrorRecord1\",\"fields\":[]},{\"type\":\"error\",\"name\":\"RPCMessageErrorRecord2\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"}]}],\"messages\":{\"doThrow\":{\"request\":[],\"response\":\"boolean\",\"errors\":[\"RPCMessageErrorRecord1\",\"RPCMessageErrorRecord2\"]}}}")
}