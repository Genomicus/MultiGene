-----------------------------------------------------------------------------------------------------

PATH=/opt/android-sdk/tools:/opt/gradle/2.10/bin:$PATH \
  android create project --target android-22 \
                         --name MyProjectName \
                         --path my_project/ \
                         --activity MyProject \
                         --package com.example.android.myproject \
                         --gradle --gradle-version 2.+

vim -V20vim.log MyProject.java
if fast errors message
:messages
for select file from fylesystem
:Explorer
for list adb-devices
:AndroidDevices

./gradlew tasks
./gradlew build
get /build/outputs/apk/*.apk

cd /opt/android-sdk/tools
./android
./android avd
./ddms

cd /opt/android-sdk/platform-tools
./adb
./fastboot

CLASSPATH=/opt/android-sdk/platforms/android-2.2/android.jar:$CLASSPATH \
ANDROID_SDK=/opt/android-sdk \
        vim MyProject.java

sudo apt-get install exuberant-ctags
export ANDROID_SDK=/opt/android-sdk
ctags --recurse --langmap=Java:.java --languages=Java --verbose -f ~/.vim/tags $ANDROID_SDK/sources

./gradlew clean build

PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH adb
PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH android
PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH android avd
PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH ddms
The standalone version of DDMS is deprecated.
Please use Android Device Monitor (tools/monitor) instead.
DISPLAY=10.0.0.48:0 PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH monitor

PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH adb install my_project-debug.apk
PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH adb uninstall com.example.android.myproject
/my_project/build/outputs/apk$ PATH=/opt/android-sdk/platform-tools:/opt/android-sdk/tools:$PATH adb install -r my_project-debug.apk

-----------------------------------------------------------------------------------------------------

susccess android autocomplete2: https://github.com/artur-shaik/vim-javacomplete2/issues/177
see info in pdf: /my_project/docs/issues/unable_to_fetch_classes_from_jar_file.pdf

https://github.com/artur-shaik/vim-javacomplete2#configuration
https://github.com/artur-shaik/vim-javacomplete2#commands
:JCdebugEnableLogs     - enable logs;
  :JCdebugDisableLogs  - disable logs;
:JCdebugGetLogContent  - get debug logs;
:JCcacheClear          - clear cache manually.
https://github.com/artur-shaik/vim-javacomplete2/issues/177:
- That solved my problem, was it that the cache is not updated?
- Yes, the cache updateed by the last update time of pom.xml or build.gradle,
  the plugin will compare it with the time of the cache file.

for additional info: https://github.com/artur-shaik/vim-javacomplete2/issues/225
see info in pdf: /my_project/docs/issues/no_longer_completes_classes_on_android.pdf
and: https://github.com/artur-shaik/vim-javacomplete2/issues/60
and pdf: /my_project/docs/issues/freeze_on_completion.pdf

https://github.com/hsanson/vim-android/blob/master/doc/vim-android.txt
http://vim.wikia.com/wiki/Omni_completion
http://vimdoc.sourceforge.net/htmldoc/insert.html#ins-completion
:help ins-completion

How do I map ctrl x ctrl o to ctrl space in terminal vim?
http://stackoverflow.com/questions/7722177/how-do-i-map-ctrl-x-ctrl-o-to-ctrl-space-in-terminal-vim

-----------------------------------------------------------------------------------------------------
