/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package example.idl

@SuppressWarnings(Array("all"))
@org.apache.avro.specific.AvroGenerated
trait RPCMessageManyParamsProtocol {
  def concatenate(a: java.lang.CharSequence, b: java.lang.CharSequence): java.lang.CharSequence
  @SuppressWarnings(Array("all"))
  trait Callback extends RPCMessageManyParamsProtocol {
    final val PROTOCOL: org.apache.avro.Protocol = example.idl.RPCMessageManyParamsProtocol.PROTOCOL
    /** @throws java.io.IOException The async call could not be completed. */
    def concatenate(a: java.lang.CharSequence, b: java.lang.CharSequence, callback: org.apache.avro.ipc.Callback[java.lang.CharSequence]): Unit
  }
}

object RPCMessageManyParamsProtocol {
  final val PROTOCOL: org.apache.avro.Protocol = org.apache.avro.Protocol.parse("{\"protocol\":\"RPCMessageManyParamsProtocol\",\"namespace\":\"example.idl\",\"types\":[],\"messages\":{\"concatenate\":{\"request\":[{\"name\":\"a\",\"type\":\"string\"},{\"name\":\"b\",\"type\":\"string\"}],\"response\":\"string\"}}}")
}