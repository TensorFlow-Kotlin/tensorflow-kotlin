package dev.tfk

import org.tensorflow.Graph
import org.tensorflow.Session
import org.tensorflow.ndarray.StdArrays
import org.tensorflow.proto.framework.GraphDef
import org.tensorflow.types.TFloat32
import java.io.File

fun Graph.predict(inputData: Array<Array<Float>>): Array<Float>? {
  val inputTensor = TFloat32.tensorOf(StdArrays.ndCopyOf(inputData))
  val outputTensor = Session(this)
    .runner()
    .feed("input", inputTensor)
    .fetch("not_activated_output")
    .run()
    .first()!!
  return arrayOf(
    outputTensor.rawData().asFloats().getFloat(0),
    outputTensor.rawData().asFloats().getFloat(1),
    outputTensor.rawData().asFloats().getFloat(2)
  )
}

object Graph {
  fun fromFile(file: String): Graph =
    Graph().apply { importGraphDef(GraphDef.parseFrom(File(file).readBytes())) }
}