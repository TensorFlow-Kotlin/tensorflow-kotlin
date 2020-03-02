package io.mattmoore.tensorflow

fun main(args: Array<String>) {
    val model = Model.load("models/simple.pb")
    val result = model.run { session ->
        val inputData = arrayOf(floatArrayOf(4f, 3f, 2f, 1f))
        // We have to create tensor to feed it to session,
        // unlike in Python where you just pass Numpy array
        val inputTensor = org.tensorflow.Tensor.create(inputData)
        val output: Array<FloatArray> = model.predict(session, inputTensor)
        output[0]
    }
    println("Results: ${result}") //should be 41. 51.5 62.
}
