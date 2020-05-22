package io.mattmoore.tensorflow

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TensorFlowSpec: StringSpec({
    "returns the TensorFlow version" {
        TensorFlow.version() shouldBe "1.15.0"
    }
})
