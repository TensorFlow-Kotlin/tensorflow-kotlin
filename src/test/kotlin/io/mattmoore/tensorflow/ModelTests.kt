package io.mattmoore.tensorflow

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ModelTests: StringSpec({
    "makes a prediction from trained model" {
        val model = Model.load("models/simple.pb")
        val result = model.run { session ->
            val inputData = arrayOf(floatArrayOf(4f, 3f, 2f, 1f))
            // We have to create tensor to feed it to session,
            // unlike in Python where you just pass Numpy array
            val inputTensor = org.tensorflow.Tensor.create(inputData)
            val output: Array<FloatArray> = model.predict(session, inputTensor)
            output[0]
        }
        result shouldBe listOf(41.0f, 51.5f, 62.0f)
    }
})