package io.mattmoore.tensorflow

import org.tensorflow.TensorFlow.version

class TensorFlow {
    companion object {
        fun version(): String {
            return org.tensorflow.TensorFlow.version()
        }
    }
}