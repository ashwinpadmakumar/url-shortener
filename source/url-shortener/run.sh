#!/bin/bash

./gradlew --status

JVM_ARGS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006"
JVM_ARGS="${JVM_ARGS} -DLOG_LEVEL=DEBUG"
JVM_ARGS="${JVM_ARGS} -noverify"
JVM_ARGS="${JVM_ARGS} -Xms128m -Xmx128m"
JVM_ARGS="${JVM_ARGS} -XX:+IdleTuningGcOnIdle"
JVM_ARGS="${JVM_ARGS} -Xtune:virtualized"
JVM_ARGS="${JVM_ARGS} -Xscmx128m"
JVM_ARGS="${JVM_ARGS} -Xscmaxaot100m"

SPRING_ARG="--spring.profiles.active=docker"

echo JVM_ARGS "${JVM_ARGS}"
echo SPRING_ARG "${SPRING_ARG}"

./gradlew bootRun --args="${SPRING_ARG}" -PjvmArgs="${JVM_ARGS}"

./gradlew --stop

