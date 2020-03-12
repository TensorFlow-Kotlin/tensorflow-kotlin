package io.mattmoore.tensorflow

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class TensorFlowTests: StringSpec({
    "returns the TensorFlow version" {
        TensorFlow.version() shouldBe "1.14.0"
    }
})