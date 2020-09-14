package dev.tfk

//import io.kotest.core.spec.style.DescribeSpec
//import org.tensorflow.Shape
//import java.util.*
//
//import org.tensorflow.op.core.Placeholder
//
//class LinearRegressionSpec : DescribeSpec({
//  describe("linear regression training") {
//    // Prepare the X data
//    val xValues = floatArrayOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f)
//    // Prepare the Y data.
//    val yValues = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
//      0.0f, 0.0f, 0.0f)
//    for ((i, x) in xValues.withIndex()) {
//      yValues[i] = 10 * x + 2 + Random(42).nextDouble().toFloat()
//    }
//
//    val X = Placeholder.create(Float::class.javaObjectType,
//      Placeholder.shape(Shape.scalar()))
//    val Y = tf.placeholder(Float::class.javaObjectType,
//      Placeholder.shape(Shape.scalar()))
//  }
//})