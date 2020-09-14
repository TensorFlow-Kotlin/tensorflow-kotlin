package dev.tfk.models

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.*

class ModelSpec : DescribeSpec({
  describe("load") {
    val model = Model.load("models/simple.pb")

    it("loads a model to run") {
      model.graphDef shouldNotBe null
    }
  }

  describe("predict") {
    val model = Model.load("models/simple.pb")
    val inputData = listOf(4f, 3f, 2f, 1f)
    val prediction = model.predict(inputData)

    it("should make a prediction") {
      prediction shouldBe listOf(41.0f, 51.5f, 62.0f)
    }
  }
})