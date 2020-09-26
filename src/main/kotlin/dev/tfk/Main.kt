package dev.tfk

fun main(args: Array<String>) {
  val graph = Graph.fromFile("models/simple.pb")
  val inputData = arrayOf(arrayOf(4f, 3f, 2f, 1f))
  val prediction = graph.predict(inputData)
  println("Prediction: ${prediction?.toList()}")
  println(TensorFlow.version())
}
