package dev.tfkotlin.models

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ImageClassifierSpec : DescribeSpec({
  describe("identifies an image") {
    val imageData = java.io.File("examples/images/animal-pet-cute-kitten-45201.jpg").readBytes()

    it("should identify the image") {
      ImageClassifier.predict(imageData) shouldBe 1
    }
  }
})