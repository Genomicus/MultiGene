#!/bin/bash

./gradlew clean build
PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH adb install -r build/outputs/apk/MultiGene-debug.apk

#PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH adb uninstall com.xpyct.counter
