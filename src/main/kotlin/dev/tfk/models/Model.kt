package dev.tfk.models

import org.tensorflow.Graph
import org.tensorflow.Session
import org.tensorflow.Tensor
import org.tensorflow.ndarray.StdArrays
import org.tensorflow.proto.framework.GraphDef
import org.tensorflow.types.TFloat32
import java.io.File

data class Model(var graphDef: ByteArray) {
  fun predict(inputData: Array<Array<Float>>): Array<Float>? {
    val inputTensor = TFloat32.tensorOf(StdArrays.ndCopyOf(inputData))
    val outputTensor = run(inputTensor)?.first()!!
    return arrayOf(
      outputTensor.rawData().asFloats().getFloat(0),
      outputTensor.rawData().asFloats().getFloat(1),
      outputTensor.rawData().asFloats().getFloat(2)
    )
  }

  private fun run(tensor: Tensor<TFloat32>): MutableList<Tensor<*>>? =
    Session(Graph().apply { importGraphDef(GraphDef.parseFrom(graphDef)) })
      .runner()
      .feed("input", tensor)
      .fetch("not_activated_output")
      .run()

  companion object {
    fun load(file: String): Model {
      val graphDef = File(file).readBytes()
      return Model(graphDef)
    }
  }
}