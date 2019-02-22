/** MACHINE-GENERATED FROM AVRO SCHEMA. DO NOT EDIT DIRECTLY */
package example.idl

@SuppressWarnings(Array("all"))
@org.apache.avro.specific.AvroGenerated
trait RPCMessageCustomTypesProtocol {
  def doNothing(s: java.lang.CharSequence, m: java.util.Map[java.lang.CharSequence, java.lang.CharSequence], l: java.util.List[java.lang.CharSequence]): Unit
  @SuppressWarnings(Array("all"))
  trait Callback extends RPCMessageCustomTypesProtocol {
    final val PROTOCOL: org.apache.avro.Protocol = example.idl.RPCMessageCustomTypesProtocol.PROTOCOL
    /** @throws java.io.IOException The async call could not be completed. */
    def doNothing(s: java.lang.CharSequence, m: java.util.Map[java.lang.CharSequence, java.lang.CharSequence], l: java.util.List[java.lang.CharSequence], callback: org.apache.avro.ipc.Callback[Unit]): Unit
  }
}

object RPCMessageCustomTypesProtocol {
  final val PROTOCOL: org.apache.avro.Protocol = org.apache.avro.Protocol.parse("{\"protocol\":\"RPCMessageCustomTypesProtocol\",\"namespace\":\"example.idl\",\"types\":[],\"messages\":{\"doNothing\":{\"request\":[{\"name\":\"s\",\"type\":\"string\"},{\"name\":\"m\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},{\"name\":\"l\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}],\"response\":\"null\"}}}")
}