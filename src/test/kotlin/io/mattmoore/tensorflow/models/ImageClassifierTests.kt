package io.mattmoore.tensorflow.models

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.mattmoore.kimage.ImageType

import io.mattmoore.kimage.KImage

class ImageClassifierTests: StringSpec({
    "identifies an image" {
        val imageData = java.io.File("examples/images/animal-pet-cute-kitten-45201.jpg").readBytes()
//        val imageData = KImage.load("examples/images/animal-pet-cute-kitten-45201.jpg", ImageType.JPEG)
        val prediction = ImageClassifier.predict(imageData)
        prediction shouldBe 1
    }
})