import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jfrog.gradle.plugin.artifactory.dsl.*
import groovy.lang.*

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("org.bytedeco.gradle-javacpp-platform") version "1.5.4"

  application

  // id("com.github.johnrengelman.shadow") version "6.0.0"

  `maven-publish`

  id("com.jfrog.bintray") version "1.8.4"
  id("com.jfrog.artifactory") version "4.15.2"

  id("model-downloader-plugin")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
  useJUnitPlatform()
}

val tensorflowVersion = "2.3.1"
val javaSigVersion = "0.3.0"
val libraryVersion = "0.1.0"
val fullVersionString = "$tensorflowVersion-$libraryVersion-SNAPSHOT"
group = "dev.tfk"
version = fullVersionString

repositories {
  jcenter()
  mavenCentral()
  maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
  // Use the Kotlin JDK 8 standard library.
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  // Kotest
  testImplementation("io.kotest:kotest-runner-junit5-jvm:4.4.3")

  // Java TensorFlow API
  implementation("org.tensorflow:ndarray:$javaSigVersion")
  implementation("org.tensorflow:tensorflow-core-platform:$javaSigVersion")

//    // Java library for HDF5 files. Used for writing models to file.
//    implementation("cisd:jhdf5:14.12.6")

  // SLF4J
  implementation("org.slf4j:slf4j-simple:1.7.30")
}

application {
  // Define the main class for the application.
  mainClassName = "dev.tfk.MainKt"
}

java {
  withSourcesJar()
  withJavadocJar()
}

publishing {
  publications {
    create<MavenPublication>("tensorflow-kotlin") {
      from(components["java"])
      groupId = "dev.tfk"
      artifactId = "tensorflow-kotlin"
      version = fullVersionString
    }
  }
}

artifactory {
  setContextUrl("https://oss.jfrog.org/artifactory")
  publish(delegateClosureOf<PublisherConfig> {
    repository(delegateClosureOf<GroovyObject> {
      setProperty("repoKey", "oss-snapshot-local")
      setProperty("username", System.getenv("BINTRAY_USER"))
      setProperty("password", System.getenv("BINTRAY_PASS"))
      setProperty("maven", true)
    })
    defaults(delegateClosureOf<GroovyObject> {
      invokeMethod("publications", "java")
      setProperty("publishArtifacts", true)
      setProperty("publishPom", true)
    })
  })
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
      name = fullVersionString
    }
  }
}

tasks.named<dev.tfk.model.plugin.DownloadModelTask>("downloadModel") {
  sourceUrl = "https://storage.googleapis.com/download.tensorflow.org/models/tflite/posenet_mobilenet_v1_100_257x257_multi_kpt_stripped.tflite"
  target = "models/123_posenet_model.tflite"
}
