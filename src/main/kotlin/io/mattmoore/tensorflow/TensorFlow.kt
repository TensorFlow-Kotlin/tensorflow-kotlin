package io.mattmoore.tensorflow

import org.tensorflow.Session
import org.tensorflow.TensorFlow


fun graphExample() {
    org.tensorflow.Graph().use { g ->
        val value = "Hello from " + TensorFlow.version()
        org.tensorflow.Tensor.create(value.toByteArray(charset("UTF-8"))).use { t ->
            // The Java API doesn't yet include convenience functions for adding operations.
            g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build()
        }
        Session(g).use { s -> s.runner().fetch("MyConst").run()[0].use { output -> println(String(output.bytesValue())) } }
    }
}

fun predict(args: Array<String>) {
    val graph = java.io.File("saved_model.pb").readBytes()

    org.tensorflow.Graph().use { g ->
        g.importGraphDef(graph)
        org.tensorflow.Session(g).use { sess ->
            val inputData = arrayOf(floatArrayOf(4f, 3f, 2f, 1f))
            // We have to create tensor to feed it to session,
            // unlike in Python where you just pass Numpy array
            val inputTensor = org.tensorflow.Tensor.create(inputData)
            val output: Array<FloatArray> = predict(sess, inputTensor)
            for (i in 0 until output[0].size) {
                println(output[0][i]) //should be 41. 51.5 62.
            }
        }
    }
}

fun predict(sess: Session, inputTensor: org.tensorflow.Tensor<*>): Array<FloatArray> {
    val result: org.tensorflow.Tensor<*> = sess.runner()
            .feed("input", inputTensor)
            .fetch("not_activated_output").run().get(0)
    val outputBuffer = Array(1) { FloatArray(3) }
    result.copyTo(outputBuffer)
    return outputBuffer
}
