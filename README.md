# TensorFlow Kotlin

This library is a Kotlin API for https://www.tensorflow.org. It attempts to provide most of the functionality provided by the official Python API, while at the same type being strongly-typed and adding some new features. It is a work in progress and primarily for research purposes.

Please refer to the main website https://tensorflow-kotlin.dev for documentation and tutorials. Here are some useful links to get started:

- [Get Started](https://tensorflow-kotlin.dev/get-started)
- [Documentation](https://tensorflow-kotlin.dev/docs)

If you have any questions or would like to contribute, please feel free to contact the project maintainer, Matt Moore, at matt@mattmoore.io.

## Current State of Affairs

This library is in its infancy. I am currently tinkering with many different ideas, some of which make their way into this repo. This project will involve a lot of work. As you'll see, I really have just a basic example of running a pre-trained model, which many tutorials around the web have already demonstrated.

I am currently examining the best way to design this project. While my focus here is on Kotlin JVM and eventually Native, I would like to build this in a platform and language-agnostic way so that other languages can make use of it.

### Not Just TensorFlow

Additionally, while this project is all about bringing TensorFlow to Kotlin, My ultimate goal is to build a full ML tool, including functionality for preparing and managing your data. Likely this repo will focus on the core TensorFlow capabilities and I will probably spin up another repo for the rest of the ML toolkit to make use of this library.

## Developer Setup

### Install TensorFlow on macOS

1. `pip3 install tensorflow==1.15.0`.
1. Get the TensorFlow JNI shared library and extract it:
```bash
wget <LINK FOR YOUR PLATFORM FROM https://www.tensorflow.org/install/lang_java>
tar -C /usr/local/lib/ -xvf libtensorflow_jni-cpu-darwin-x86_64-1.15.0.tar.gz
```
1. `git clone` this project.
1. `./gradlew build`. To run tests: `./gradlew test`.
