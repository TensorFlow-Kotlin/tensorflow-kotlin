package io.mattmoore.tensorflow

class Tensorflow {
    init {
      System.loadLibrary("hello")
    }

    external fun hello(): String
}
