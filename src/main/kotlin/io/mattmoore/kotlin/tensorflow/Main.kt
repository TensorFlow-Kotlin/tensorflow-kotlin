package io.mattmoore.kotlin.tensorflow

import org.tensorflow.Graph
import org.tensorflow.Session
import org.tensorflow.Tensor
import org.tensorflow.TensorFlow

fun main(args: Array<String>) {
    Graph().use { g ->
        val value = "Hello from " + TensorFlow.version()
        Tensor.create(value.toByteArray(charset("UTF-8"))).use { t ->
            // The Java API doesn't yet include convenience functions for adding operations.
            g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build()
        }
        Session(g).use { s -> s.runner().fetch("MyConst").run()[0].use { output -> println(String(output.bytesValue())) } }
    }
}
