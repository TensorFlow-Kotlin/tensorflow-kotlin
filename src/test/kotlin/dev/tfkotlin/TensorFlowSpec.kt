package dev.tfkotlin

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TensorFlowSpec : DescribeSpec({
  describe("version") {
    it("should return the version of TensorFlow") {
      TensorFlow.version() shouldBe "1.15.0"
    }
  }
})
