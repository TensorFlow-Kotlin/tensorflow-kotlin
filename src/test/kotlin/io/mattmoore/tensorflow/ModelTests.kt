package io.mattmoore.tensorflow

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ModelTests: StringSpec({
    "makes a prediction from trained model" {
        val model = Model.load("models/simple.pb")
        val inputData = arrayOf(floatArrayOf(4f, 3f, 2f, 1f))
        val prediction = model.predict(inputData)
        prediction shouldBe listOf(41.0f, 51.5f, 62.0f)
    }
})