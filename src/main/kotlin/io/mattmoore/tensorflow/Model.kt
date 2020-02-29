package io.mattmoore.tensorflow

import org.tensorflow.Session

class Model(graphDef: ByteArray) {
    var graphDef: ByteArray = graphDef

    fun predict(sess: Session, inputTensor: org.tensorflow.Tensor<*>): Array<FloatArray> {
        val result: org.tensorflow.Tensor<*> = sess.runner()
                .feed("input", inputTensor)
                .fetch("not_activated_output").run().get(0)
        val outputBuffer = Array(1) { FloatArray(3) }
        result.copyTo(outputBuffer)
        return outputBuffer
    }

    fun run(lambda: (session: org.tensorflow.Session) -> Unit) {
        org.tensorflow.Graph().use { g ->
            g.importGraphDef(graphDef)
            org.tensorflow.Session(g).use { session ->
                lambda(session)
            }
        }
    }

    companion object {
        fun load(file: String): Model {
            val graphDef = java.io.File(file).readBytes()
            return Model(graphDef)
        }

        fun save(file: String) {

        }
    }
}