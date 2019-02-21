/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package example.idl

@SuppressWarnings(Array("all"))
@org.apache.avro.specific.AvroGenerated
trait RPCMessageNoParamsProtocol {
  def random(): Int
  @SuppressWarnings(Array("all"))
  trait Callback extends RPCMessageNoParamsProtocol {
    final val PROTOCOL: org.apache.avro.Protocol = example.idl.RPCMessageNoParamsProtocol.PROTOCOL
    /** @throws java.io.IOException The async call could not be completed. */
    def random(callback: org.apache.avro.ipc.Callback[Int]): Unit
  }
}

object RPCMessageNoParamsProtocol {
  final val PROTOCOL: org.apache.avro.Protocol = org.apache.avro.Protocol.parse("{\"protocol\":\"RPCMessageNoParamsProtocol\",\"namespace\":\"example.idl\",\"types\":[],\"messages\":{\"random\":{\"request\":[],\"response\":\"int\"}}}")
}