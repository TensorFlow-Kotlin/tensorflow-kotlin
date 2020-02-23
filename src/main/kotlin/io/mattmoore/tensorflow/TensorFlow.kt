package io.mattmoore.tensorflow

import org.tensorflow.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

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

// Training a trivial linear model.
fun train(args: Array<String>) {
    if (args.size != 2) {
        System.err.println("Require two arguments: The GraphDef file and checkpoint directory")
        System.exit(1)
    }
    val graphDef = Files.readAllBytes(Paths.get(args[0]))
    val checkpointDir = args[1]
    val checkpointExists = Files.exists(Paths.get(checkpointDir))
    org.tensorflow.Graph().use { graph ->
        Session(graph).use { sess ->
            Tensors.create(Paths.get(checkpointDir, "ckpt").toString()).use { checkpointPrefix ->
                graph.importGraphDef(graphDef)
                // Initialize or restore.
                // The names of the tensors in the graph are printed out by the program
                // that created the graph:
                // https://github.com/tensorflow/models/blob/master/samples/languages/java/training/model/create_graph.py
                if (checkpointExists) {
                    sess.runner().feed("save/Const", checkpointPrefix).addTarget("save/restore_all").run()
                } else {
                    sess.runner().addTarget("init").run()
                }
                print("Starting from       : ")
                printVariables(sess)
                // Train a bunch of times.
                // (Will be much more efficient if we sent batches instead of individual values).
                val r = Random()
                val NUM_EXAMPLES = 500
                for (i in 1..5) {
                    for (n in 0 until NUM_EXAMPLES) {
                        val `in` = r.nextFloat()
                        Tensors.create(`in`).use { input ->
                            Tensors.create(3 * `in` + 2).use { target ->
                                // Again the tensor names are from the program that created the graph.
                                // https://github.com/tensorflow/models/blob/master/samples/languages/java/training/model/create_graph.py
                                sess.runner().feed("input", input).feed("target", target).addTarget("train").run()
                            }
                        }
                    }
                    System.out.printf("After %5d examples: ", i * NUM_EXAMPLES)
                    printVariables(sess)
                }
                // Checkpoint.
                // The feed and target name are from the program that created the graph.
                // https://github.com/tensorflow/models/blob/master/samples/languages/java/training/model/create_graph.py.
                sess.runner().feed("save/Const", checkpointPrefix).addTarget("save/control_dependency").run()
                Tensors.create(1.0f).use { input ->
                    sess.runner().feed("input", input).fetch("output").run()[0].expect(Float::class.java).use { output ->
                        System.out.printf(
                                "For input %f, produced %f (ideally would produce 3*%f + 2)\n",
                                input.floatValue(), output.floatValue(), input.floatValue())
                    }
                }
            }
        }
    }
}

private fun printVariables(sess: org.tensorflow.Session) {
    val values = sess.runner().fetch("W/read").fetch("b/read").run()
    System.out.printf("W = %f\tb = %f\n", values[0].floatValue(), values[1].floatValue())
    for (t in values) {
        t.close()
    }
}
