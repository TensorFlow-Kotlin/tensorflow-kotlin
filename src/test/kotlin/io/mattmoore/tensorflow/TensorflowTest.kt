package io.mattmoore.tensorflow

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class TensorflowTest {
    @Test
    fun testHello() {
        assertEquals(Tensorflow().hello(), "Hello World!")
        println(Tensorflow().hello())
    }
}
