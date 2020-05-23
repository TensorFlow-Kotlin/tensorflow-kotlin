plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    jcenter()
    mavenCentral()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

group = "dev.tfk.plugin"
version = "1.0"

dependencies {
    compileOnly(gradleApi())
    implementation("com.squareup.okhttp3:okhttp:4.2.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
}

gradlePlugin {
    plugins {
        register("ModelDownloader") {
            id = "model-downloader-plugin"
            implementationClass = "dev.tfk.model.plugin.TensorflowModelDownloaderPlugin"
        }
    }
}