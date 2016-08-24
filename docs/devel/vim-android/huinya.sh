#!/bin/bash

export JAVA_HOME=/opt/jdk1.8.0_65
export JDK_HOME=/opt/jdk1.8.0_65
export JRE_HOME=/opt/jdk1.8.0_65/jre
export PATH=/opt/jdk1.8.0_65/bin:/opt/jdk1.8.0_65/jre/bin:$PATH

#javac -version
#javac -help
javac -d ~/.vim/bundle/vim-javacomplete2/libs/javavi/target/classes -classpath ~/.vim/bundle/vim-javacomplete2/libs/javavi/target/classes:~/.vim/bundle/vim-javacomplete2/libs/javaparser.jar: -sourcepath ~/.vim/bundle/vim-javacomplete2/libs/javavi/src/main/java -g -nowarn -target 1.8 -source 1.8 -encoding UTF-8 -Xlint:unchecked ~/.vim/bundle/vim-javacomplete2/libs/javavi/src/main/java/kg/ash/javavi/Javavi.java

