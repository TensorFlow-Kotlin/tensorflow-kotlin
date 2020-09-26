package dev.tfk.models

import dev.tfk.Graph
import dev.tfk.predict
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ModelSpec : DescribeSpec({
  describe("load") {
    val graph = Graph.fromFile("models/simple.pb")

    it("loads a model to run") {
      graph shouldNotBe null
    }
  }

  describe("predict") {
    val graph = Graph.fromFile("models/simple.pb")
    val inputData = arrayOf(arrayOf(4f, 3f, 2f, 1f))
    val prediction = graph.predict(inputData)

    it("should make a prediction") {
      prediction shouldBe arrayOf(41.0f, 51.5f, 62.0f)
    }
  }
})