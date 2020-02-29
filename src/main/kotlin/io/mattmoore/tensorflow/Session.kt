package io.mattmoore.tensorflow

class Session(graph: Graph) {
//    init {
//        org.tensorflow.Graph().use { graphDef ->
//            graphDef.importGraphDef(graph.graphDef)
//            session = org.tensorflow.Session(graphDef)
//        }
//    }

//    fun run(lambda: (session: org.tensorflow.Session) -> Unit) {
//        session.use { session ->
//            lambda(session)
//        }
//    }

//    fun run(lambda: (session: org.tensorflow.Session) -> Unit) {
//        org.tensorflow.Graph().use { g ->
//            g.importGraphDef(this.graph.graphDef)
//            org.tensorflow.Session(g).use(lambda)
//        }
//    }
}
