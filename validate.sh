#!/bin/bash
#==============================================================================
#
#          FILE:  validate.sh
#
#         USAGE:  ./validate.sh
#
#   DESCRIPTION: Build TullamoreQA and all of it's components.
#                Will run Unit Tests, Integration Tests, Javadocs, Cobertura and Checkstyle.
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

# Confirm Docker is installed.

if type -p docker; then
    _docker=docker
else
    echo "Docker is not installed. Docker is required for the
    TullamoreQA Integration Tests."
    exit 1
fi

# Run build.sh

./build.sh

# Execute build of TQA

mvn checkstyle:check javadoc:javadoc cobertura:cobertura -PdockerIT verify
