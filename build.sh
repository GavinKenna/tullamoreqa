#!/bin/bash
#==============================================================================
#
#          FILE:  build.sh
#
#         USAGE:  ./build.sh
#
#   DESCRIPTION: Build TullamoreQA and all of it's components.
#                Will run Unit Tests, BUT NOT
#                integration tests (nor Javadocs / Checkstyle).
#
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR:  Gavin Kenna <thegavinkenna@gmail.com>, gkenna
#       COMPANY:  Gavin Kenna
#       VERSION:  1.0
#       CREATED:  15/08/2018
#      REVISION:  ---
#==============================================================================

# Confirm Java is installed.

if type -p java; then
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    _java="$JAVA_HOME/bin/java"
else
    echo "Java cannot be found within the PATH or $JAVA_HOME/bin/java"
    exit 1
fi

# Confirm Java 1.8+ is installed.

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ "$version" < "1.8" ]]; then
        echo Version of Java is less than 1.8. TullamoreQA requires Java 8 to build.
        exit 1
    fi
fi

# Confirm Maven is installed.

if type -p mvn; then
    _mvn=mvn
elif [[ -n "$M2_HOME" ]] && [[ -x "$M2_HOME/bin/mvn" ]];  then
    _mvn="$M2_HOME/bin/mvn"
else
    echo "Maven cannot be found within the PATH or $M2_HOME/bin/mvn"
    exit 1
fi

# Execute build of TQA

mvn clean install
