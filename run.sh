#!/bin/bash

echo $JAVA_HOME
java  -cp ./out/production/DrivingReport DrivingReportApplication "$@"