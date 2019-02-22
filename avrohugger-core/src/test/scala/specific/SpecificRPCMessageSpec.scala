package avrohugger
package test
package specific

import java.io.File

import avrohugger.format.SpecificRecord
import avrohugger.types._
import org.specs2._
import org.specs2.execute.Result

/**
  * Test generating different RPC messages.
  */
class SpecificRPCMessageSpec extends Specification {
  val expectedDir = s"avrohugger-core/src/test/expected/specific/rpc"

  def runTest(avdlName: String, scalaNames: Seq[String], maybeCustomTypes: Option[AvroScalaTypes] = None): Result = {
    require(scalaNames.nonEmpty, "The test required 'scalaNames: Seq[String]' not to be empty.")
    val generator = new Generator(SpecificRecord, avroScalaCustomTypes = maybeCustomTypes)
    val outputDir = s"${generator.defaultOutputDir}/specific/rpc"
    val avdl = new File(s"avrohugger-core/src/test/avro/${avdlName}.avdl")
    generator.fileToFile(avdl, outputDir)
    Result.foreach(scalaNames) { scalaName =>
      val generated = util.Util.readFile(s"${outputDir}/example/idl/${scalaName}.scala")
      val expected = util.Util.readFile(s"${expectedDir}/example/idl/${scalaName}.scala")
      generated === expected
    }
  }

  def is = s2"""
    A Specific Generator should
      generate classes with RPC messages
        with no params ${runTest("rpc_message_no_params", Seq("RPCMessageNoParamsProtocol"))}
        with many params ${runTest("rpc_message_many_params", Seq("RPCMessageManyParamsProtocol"))}
      generate correct error records ${
        runTest("rpc_message_error_record",
          Seq("RPCMessageErrorRecordProtocol", "RPCMessageErrorRecord1", "RPCMessageErrorRecord2"))
      }
      generate classes with custom types ${
        runTest("rpc_message_custom_types",
          Seq("RPCMessageCustomTypesProtocol"),
          Some(SpecificRecord.defaultTypes.copy(
            string = JavaString,
            `null` = ScalaUnit,
            map = JavaMap,
            array = JavaList
          )))
      }
  """
}
