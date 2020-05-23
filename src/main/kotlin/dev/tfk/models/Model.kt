package dev.tfk.models

import org.tensorflow.Graph
import org.tensorflow.Session
import org.tensorflow.Tensor

import java.io.File

class Model(graphDef: ByteArray) {
    var graphDef: ByteArray = graphDef

    fun predict(inputData: Array<FloatArray>): List<Float> {
        lateinit var output: Array<FloatArray>
        return Graph().use { g ->
            g.importGraphDef(graphDef)
            Session(g).use { session ->
                val inputTensor = Tensor.create(inputData)
                output = run(session, inputTensor)
            }
            output[0].toList()
        }
    }

    companion object {
        fun load(file: String): Model {
            val graphDef = File(file).readBytes()
            return Model(graphDef)
        }
    }

    private fun run(session: Session, inputTensor: Tensor<*>): Array<FloatArray> {
        val result: Tensor<*> = session.runner()
                .feed("input", inputTensor)
                .fetch("not_activated_output").run()[0]
        val outputBuffer = Array(1) { FloatArray(3) }
        result.copyTo(outputBuffer)
        return outputBuffer
    }
}