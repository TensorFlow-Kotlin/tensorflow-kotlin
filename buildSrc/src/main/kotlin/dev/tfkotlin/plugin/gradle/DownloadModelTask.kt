package dev.tfkotlin.model.plugin

import okhttp3.OkHttpClient
import okhttp3.Request
import okio.BufferedSink
import okio.buffer
import okio.sink
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

open class DownloadModelTask : DefaultTask() {

    @get:Input
    lateinit var sourceUrl: String

    @OutputFile
    lateinit var target: String

    init {
        group = "prepare" // This will be the group name for your task.
        description = "Download Tensorflow model file"
    }

    @TaskAction // Marks a function as the action to run when the task is executed.
    fun run() {
        val client = OkHttpClient()

        val request = Request.Builder().url(sourceUrl).build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val sink: BufferedSink = File("${project.projectDir}/$target").sink().buffer()
            sink.writeAll(response.body!!.source())
            sink.close()
        } else {
            throw GradleException("Error downloaded file at $sourceUrl with code ${response.code}")
        }
    }
}
