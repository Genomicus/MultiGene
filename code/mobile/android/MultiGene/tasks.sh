#!/bin/bash

export JDK_HOME=/opt/jdk1.8.0_65
export JAVA_HOME=/opt/jdk1.8.0_65/jre
export ANDROID_SDK=/opt/android-sdk

case $1 in
    "build"  )
        ./gradlew build
        ;;
    "rebuild")
        ./gradlew clean build
        ;;
    "deploy"|"redeploy")
        PATH=$ANDROID_SDK/platform-tools:$ANDROID_SDK/tools:$PATH adb install -r build/outputs/apk/MultiGene-debug.apk
        ;;
    "uninstall" )
        PATH=$ANDROID_SDK/platform-tools:$ANDROID_SDK/tools:$PATH adb uninstall com.xpyct.multigene
        ;;
    "connect")
        PATH=$ANDROID_SDK/platform-tools:$ANDROID_SDK/tools:$PATH adb connect $2
        ;;
    "devices")
        PATH=$ANDROID_SDK/platform-tools:$ANDROID_SDK/tools:$PATH adb devices
        ;;
    "tcpip"  )
        PATH=$ANDROID_SDK/platform-tools:$ANDROID_SDK/tools:$PATH adb tcpip 5555
        ;;
    "restart")
        PATH=$ANDROID_SDK/platform-tools:$ANDROID_SDK/tools:$PATH adb kill-server
        PATH=$ANDROID_SDK/platform-tools:$ANDROID_SDK/tools:$PATH adb start-server
        ;;
    "cscope" )
        find . -name '*.py' \
            -o -name '*.java' \
            -o -iname '*.[CH]' \
            -o -name '*.cpp' \
            -o -name '*.cc' \
            -o -name '*.hpp'  \
        > cscope.files
        # -b: just build
        # -q: create inverted index
        cscope -b -q
        ;;
    "ctags"  )
        ctags --recurse --langmap=Java:.java --languages=Java --verbose -f ~/.vim/tags $ANDROID_SDK/sources
        ;;
    *        )
        echo "use: $0 rebuild"
        echo "     $0 clean"
        echo "     $0 deploy"
        echo "     $0 uninstall"
        echo "     $0 connect <IP-android-device>"
        echo "     $0 devices"
        echo "     $0 tcpip"
        echo "     $0 restart"
        echo "     $0 cscope"
        ;;
esac
