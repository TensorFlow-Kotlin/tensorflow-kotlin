package io.mattmoore.tensorflow

class Graph(graphDef: ByteArray) {
    var graphDef: ByteArray = graphDef

    companion object {
        fun load(file: String): io.mattmoore.tensorflow.Graph {
            return Graph(java.io.File(file).readBytes())
        }

        fun save(graph: Graph, file: String) {
            // TODO: Not yet implemented.
        }
    }
}
