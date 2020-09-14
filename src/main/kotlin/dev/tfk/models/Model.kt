package dev.tfk.models

import org.tensorflow.DataType
import org.tensorflow.Graph
import org.tensorflow.Session
import org.tensorflow.Tensor
import org.tensorflow.proto.framework.GraphDef
import org.tensorflow.proto.framework.TensorShapeProto
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32

import java.io.File

class Model(graphDef: ByteArray) {
  var graphDef: ByteArray = graphDef

  fun predict(inputData: List<Float>): Tensor<*>? =
    Session(Graph().apply { importGraphDef(GraphDef.parseFrom(graphDef)) })
      .runner()
      .feed("input", TFloat32.vectorOf(*inputData.toFloatArray()))
      .fetch("not_activated_output")
      .run()[0]
//    lateinit var output: Array<FloatArray>
//    return Graph().use { g ->
//      g.importGraphDef(GraphDef.parseFrom(graphDef))
//      Session(g).use { session ->
//        val inputTensor = TFloat32.vectorOf(*inputData.toFloatArray())
//        output = run(session, inputTensor)
//      }
//      output.first().toList()
//    }

  companion object {
    fun load(file: String): Model {
      val graphDef = File(file).readBytes()
      return Model(graphDef)
    }
  }

//  private fun run(session: Session, inputTensor: Tensor<*>) {
//    session.runner()
//      .feed("input", inputTensor)
//      .fetch("not_activated_output").run()[0]
////    val outputBuffer = Array(1) { FloatArray(3) }
////    result.copyTo(outputBuffer)
////    return outputBuffer
//  }
}