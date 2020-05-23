import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm")

    // Apply the application plugin to add support for building a CLI application.
    application

    // Maven publish
    `maven-publish`

    // Bintray
    id("com.jfrog.bintray") version "1.8.4"

    // model downloader
    id("model-downloader-plugin")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val tensorflowVersion = "1.15.0"
val libraryVersion = "${tensorflowVersion}-0.1.0"

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
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.5")

    // Java TensorFlow API
    implementation("org.tensorflow:tensorflow:$tensorflowVersion")

//    // Java library for HDF5 files. Used for writing models to file.
//    implementation("cisd:jhdf5:14.12.6")

    // SLF4J
    implementation("org.slf4j:slf4j-simple:1.7.30")
}

application {
    // Define the main class for the application.
    mainClassName = "dev.tfk.MainKt"
}

publishing {
    publications {
        create<MavenPublication>("tensorflow-kotlin") {
            from(components["java"])
            groupId = "dev.tfk"
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

tasks.named<dev.tfk.model.plugin.DownloadModelTask>("downloadModel") {
    sourceUrl = "https://storage.googleapis.com/download.tensorflow.org/models/tflite/posenet_mobilenet_v1_100_257x257_multi_kpt_stripped.tflite"
    target = "models/123_posenet_model.tflite"
}
