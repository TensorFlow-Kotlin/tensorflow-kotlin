package io.mattmoore.tensorflow

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ModelTests: StringSpec({
    "should run" {
        "hello".length shouldBe 5
    }
})