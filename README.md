# TensorFlow Kotlin

This library is a Kotlin API for https://www.tensorflow.org. It attempts to provide most of the functionality provided by the official Python API, while at the same type being strongly-typed and adding some new features. It is a work in progress and primarily for research purposes.

Please refer to the main website https://tensorflow-kotlin.dev for documentation and tutorials. Here are some useful links to get started:

- [Get Started](https://tensorflow-kotlin.dev/get-started)
- [Documentation](https://tensorflow-kotlin.dev/docs)

If you have any questions or would like to contribute, please feel free to contact the project maintainer, Matt Moore, at matt@mattmoore.io.

## Developer Setup

### Install TensorFlow on macOS

1. `pip3 install tensorflow==1.14.0`.
1. Get the TensorFlow JNI shared library and extract it:
```bash
wget <LINK FOR YOUR PLATFORM FROM https://www.tensorflow.org/install/lang_java>
tar -C /usr/local/lib/ -xvf libtensorflow_jni-cpu-darwin-x86_64-1.14.0.tar.gz
```
1. `git clone` this project.
1. `./gradlew build`. To run tests: `./gradlew test`.
