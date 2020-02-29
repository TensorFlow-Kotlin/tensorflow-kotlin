import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.60")

    // Apply the application plugin to add support for building a CLI application.
    application

    // Shadow Jar
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    mavenCentral()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Kotest
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.0")

    // Java TensorFlow API
    implementation("org.tensorflow:tensorflow:1.14.0")

//    // Java library for HDF5 files. Used for writing models to file.
//    implementation("cisd:jhdf5:14.12.6")
}

application {
    // Define the main class for the application.
    mainClassName = "io.mattmoore.tensorflow.MainKt"
}
