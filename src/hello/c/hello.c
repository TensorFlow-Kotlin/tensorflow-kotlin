#include <jni.h>
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_dev_tfkotlin_Tensorflow_hello(JNIEnv *env, jobject obj) {
  return (*env)->NewStringUTF(env, "Hello World!");
}
