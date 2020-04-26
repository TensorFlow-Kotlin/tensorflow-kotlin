package io.mattmoore.tensorflow.models

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ImageClassifierTests: StringSpec({
    "identifies an image" {
        val imageData = java.io.File("examples/images/animal-pet-cute-kitten-45201.jpg").readBytes()
        val prediction = ImageClassifier.predict(imageData)
        prediction shouldBe 1
    }
})