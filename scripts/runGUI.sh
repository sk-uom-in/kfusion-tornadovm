#!/bin/bash

echo "kfusion -Xmx512m -Xms512m -Dtornado.benchmarking=False kfusion.tornado.GUIFX"

export KFUSION_ROOT="${PWD}"
export TORNADO_VM_PATH=/Users/shivomkhare/UomSam/TornadoVM/bin/sdk/modules

JARS=$(echo ${KFUSION_ROOT}/target/*.jar | tr ' ' ':')

JFLAGS="--add-exports=javafx.graphics/com.sun.javafx.util=ALL-UNNAMED -Xmx512m -Xms512m -Dtornado.kernels.coarsener=False -Dtornado.enable.fix.reads=False -Dlog4j.configurationFile=${KFUSION_ROOT}/conf/log4j2.xml -Dtornado.benchmarking=False -Dtornado.profiler=False -Dtornado.log.profiler=False"

CLASSPATH=${KFUSION_ROOT}/target/classes:${JARS}:${TORNADO_VM_PATH}/*

tornado --jvm="${JFLAGS}" -cp "${CLASSPATH}" kfusion.java.GUIFX
