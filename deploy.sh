#!/bin/bash
set -e

BRANCH=${TRAVIS_BRANCH:-$(git rev-parse --abbrev-ref HEAD)} 

if [[ $BRANCH == 'master' ]]; then
  STAGE="prod"
elif [[ $BRANCH == 'develop' ]]; then
  STAGE="dev"
fi

if [ -z ${STAGE+x} ]; then
  echo "Not deploying changes";
  exit 0;
fi

echo "Deploying from branch $BRANCH to stage $STAGE"

./gradlew build
if [ $? -ne 0 ]; then
  echo "Failed to build changes";
  exit 1;
fi

sls deploy --stage $STAGE --region $AWS_REGION
if [ $? -ne 0 ]; then
  echo "Failed to deploy changes";
  exit 1;
fi
