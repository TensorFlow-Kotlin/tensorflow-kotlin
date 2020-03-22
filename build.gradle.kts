//import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.60")

    // Apply the application plugin to add support for building a CLI application.
    application

    // Shadow Jar
//    id("com.github.johnrengelman.shadow") version "5.2.0"

    // Maven publish
    `maven-publish`

    // Bintray
    id("com.jfrog.bintray") version "1.8.4"
}

val kotlinVersion = "1.3.60"
val tensorflowVersion = "1.14.0"
val libraryVersion = "${tensorflowVersion}-0.1.0"

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/mattmoore/kimage")
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Kotest
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
//    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.0-BETA1")

    // Java TensorFlow API
    implementation("org.tensorflow:tensorflow:$tensorflowVersion")

    // KImage
    implementation("io.mattmoore:kimage:0.1.0")

//    // Java library for HDF5 files. Used for writing models to file.
//    implementation("cisd:jhdf5:14.12.6")

    // SLF4J
    implementation("org.slf4j:slf4j-simple:1.7.30")
}

application {
    // Define the main class for the application.
    mainClassName = "io.mattmoore.tensorflow.MainKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("tensorflow-kotlin") {
            from(components["java"])
            groupId = "io.mattmoore"
            artifactId = "tensorflow-kotlin"
            version = libraryVersion
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_PASS")
    setPublications("tensorflow-kotlin")
    pkg.apply {
        repo = "tensorflow-kotlin"
        name = "tensorflow-kotlin"
        setLicenses("MIT")
        vcsUrl = "https://github.com/TensorFlow-Kotlin/tensorflow-kotlin.git"
        version.apply {
            name = libraryVersion
        }
    }
}
