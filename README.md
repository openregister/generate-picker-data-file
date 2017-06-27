# Generate picker data file

This is a Java module that transforms a [register(s)](https://registers.cloudapps.digital/) into a [graph data file](https://github.com/alphagov/openregister-picker-engine#how-it-works).

[Jackson](https://github.com/FasterXML/jackson) is used to serialize objects to and from JSON.

## Requirements

- Java 8
- Gradle 3.2+
- Node 8 (optional, for [serverless](https://github.com/serverless/serverless) deployment)

On macOS:

```bash
brew cask install java
brew install gradle
npm install -g serverless # optional, only if you want to deploy it to AWS Lambda
```

## Building and testing

To build, run `gradle wrapper` to build the gradle wrapper jar, and from then on:

```bash
./gradlew build
```

The expected result should be similar to:

```bash
Starting a Gradle Daemon, 1 incompatible Daemon could not be reused, use --status for details
:compileJava
:processResources
:classes
:jar
:assemble
:buildZip
:compileTestJava UP-TO-DATE
:processTestResources UP-TO-DATE
:testClasses UP-TO-DATE
:test UP-TO-DATE
:check UP-TO-DATE
:build

BUILD SUCCESSFUL

Total time: 8.195 secs
```

To run the tests in watch mode:

```bash
./gradlew --continuous test
```

## Deploying

You can build and deploy in one command using:

```bash
./deploy.sh
```

You'll need some environment variables (you can store these in `./.env` and `source .env`):

```bash
export AWS_REGION=eu-west-2
export AWS_ACCESS_KEY_ID=XXX
export AWS_SECRET_ACCESS_KEY=YYY
export STAGE=dev
```

The expected result should be similar to:

```bash
Serverless: Creating Stack...
Serverless: Checking Stack create progress...
.....
Serverless: Stack create finished...
Serverless: Uploading CloudFormation file to S3...
Serverless: Uploading service .zip file to S3...
Serverless: Updating Stack...
Serverless: Checking Stack update progress...
..............................
Serverless: Stack update finished...
Service Information
service: picker-data
stage: dev
region: eu-west-2
api keys:
  None
endpoints:
  GET - https://XXXXXXX.execute-api.eu-west-2.amazonaws.com/dev/fetch
functions:
  picker-data-dev-currentTime: arn:aws:lambda:eu-west-2:XXXXXXX:function:picker-data-dev-currentTime
```

The AWS IAM user needs to have at least the permissions specified by the [picker-data-\_star\_-eu-west-2-policy.json](picker-data-_star_-eu-west-2-policy.json) in this repo. If something doesn't work and you get "Access Denied", try it with a user with `AdministratorAccess`.

## Usage

You can now invoke the Lambda function directly and see the resulting log via:

```bash
serverless invoke --function fetch --log
```

You can send an HTTP request directly to the endpoint using a tool like `curl`, Postman, or your browser:

```bash
curl https://XXXXXXX.execute-api.eu-west-2.amazonaws.com/dev/fetch
```

## Scaling

By default, AWS Lambda limits the total concurrent executions across all functions within a given region to 100. The default limit is a safety limit that protects you from costs due to potential runaway or recursive functions during initial development and testing. To increase this limit above the default, follow the steps in ["To request a limit increase for concurrent executions"](http://docs.aws.amazon.com/lambda/latest/dg/concurrent-executions.html#increase-concurrent-executions-limit).

## License

[MIT](LICENSE.txt).