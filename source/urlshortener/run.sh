#!/bin/bash

export CONTAINER_ID="${CONTAINER_ID:-local}"

./gradlew --status

#LIVE_RELOAD="${LIVE_RELOAD:-false}"
#if [ "${LIVE_RELOAD}" =  "true" ]; then
#  export GRADLE_OPTS=--daemon
#  export GRADLE_USER_HOME=/tmp/gradle_user_home
#  echo "Live Reload Enabled"
#  ./gradlew --stop
#  ./gradlew build --continuous --quiet --build-cache --parallel -x check -x test -x bootJar &
#else
#  if [ -f "/tmp/gradle_user_home/gradle.properties" ]; then
#      cp /tmp/gradle_user_home/gradle.properties ~/.gradle/
#  fi
#fi

JVM_ARGS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006"
JVM_ARGS="${JVM_ARGS} -DLOG_LEVEL=DEBUG"
JVM_ARGS="${JVM_ARGS} -noverify"
JVM_ARGS="${JVM_ARGS} -Xms128m -Xmx128m"
JVM_ARGS="${JVM_ARGS} -XX:+IdleTuningGcOnIdle"
JVM_ARGS="${JVM_ARGS} -Xtune:virtualized"
JVM_ARGS="${JVM_ARGS} -Xscmx128m"
JVM_ARGS="${JVM_ARGS} -Xscmaxaot100m"
JVM_ARGS="${JVM_ARGS} -PCONTAINER_ID=${CONTAINER_ID}"

SPRING_ARG="--spring.profiles.active=local"

echo JVM_ARGS "${JVM_ARGS}"
echo SPRING_ARG "${SPRING_ARG}"

./gradlew bootRun --args="${SPRING_ARG}" -PjvmArgs="${JVM_ARGS}"

./gradlew --stop

