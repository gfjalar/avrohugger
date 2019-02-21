package avrohugger
package test
package specific

import java.io.File

import avrohugger.format.SpecificRecord
import org.specs2._

/**
  * Test generating different RPC messages.
  */
class SpecificRPCMessageSpec extends Specification {
  val generator = new Generator(SpecificRecord)
  val outputDir = s"${generator.defaultOutputDir}/specific/rpc"
  val expectedDir = s"avrohugger-core/src/test/expected/specific/rpc"

  def runTest(avdlName: String, scalaName: String): matcher.MatchResult[String] = {
    val avdl = new File(s"avrohugger-core/src/test/avro/${avdlName}.avdl")
    generator.fileToFile(avdl, outputDir)
    val generated = util.Util.readFile(s"${outputDir}/example/idl/${scalaName}.scala")
    val expected = util.Util.readFile(s"${expectedDir}/example/idl/${scalaName}.scala")
    generated === expected
  }

  def is = s2"""
    A Specific Generator should
      generate classes with RPC messages
        with no params ${runTest("rpc_message_no_params", "RPCMessageNoParamsProtocol")}
        with many params ${runTest("rpc_message_many_params", "RPCMessageManyParamsProtocol")}
  """
}
