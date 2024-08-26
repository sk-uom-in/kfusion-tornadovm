#!/bin/bash
export KFUSION_ROOT="${PWD}"
export PATH="${PATH}:${KFUSION_ROOT}/bin"
export PATH_TO_FX=/Users/shivomkhare/Downloads/javafx-sdk-21.0-2.4/lib
export PATH="${PATH}:${PATH_TO_FX}"
mvn clean install -DskipTests