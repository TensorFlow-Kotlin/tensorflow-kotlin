package io.mattmoore.tensorflow.models

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ModelSpec: StringSpec({
    "makes a prediction from trained model" {
        val model = Model.load("models/simple.pb")
        val inputData = arrayOf(floatArrayOf(4f, 3f, 2f, 1f))
        val prediction = model.predict(inputData)
        prediction shouldBe listOf(41.0f, 51.5f, 62.0f)
    }
})