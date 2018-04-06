#!/usr/bin/env bash

# checking if arguments are valid
if [ "$1" = "develop" ] || [ "$1" = "release" ] || [ "$1" = "master" ]; then
    echo "Valid argument: $1"
elif [ "$#" -eq  "0" ]; then
    echo "ERROR: No argument supplied, valid arguments: [develop, release, master]"
    exit 1
else
    echo "ERROR: Invalid argument: $1, valid arguments: [develop, release, master]"
    exit 1
fi

echo "npm install"
npm install
echo "bower install"
bower install
echo "gulp clean"
gulp clean
echo "gulp config-deployment-$1 build"
gulp config-deployment-$1 build
echo "Copy index.html"
cp .tmp/dist/index.html .tmp/dist/portal/.
