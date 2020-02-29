package io.mattmoore.tensorflow

import org.tensorflow.Graph
import org.tensorflow.Session
import org.tensorflow.Tensor

import java.io.File

class Model(graphDef: ByteArray) {
    var graphDef: ByteArray = graphDef

    fun predict(sess: Session, inputTensor: Tensor<*>): Array<FloatArray> {
        val result: Tensor<*> = sess.runner()
                .feed("input", inputTensor)
                .fetch("not_activated_output").run().get(0)
        val outputBuffer = Array(1) { FloatArray(3) }
        result.copyTo(outputBuffer)
        return outputBuffer
    }

    fun run(lambda: (session: Session) -> FloatArray): List<Float> {
        lateinit var result: FloatArray
        return Graph().use { g ->
            g.importGraphDef(graphDef)
            Session(g).use { session ->
                result = lambda(session)
            }
            result.toList()
        }
    }

    companion object {
        fun load(file: String): Model {
            val graphDef = File(file).readBytes()
            return Model(graphDef)
        }
    }
}