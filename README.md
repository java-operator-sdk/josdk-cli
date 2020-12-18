josdk-cli
=======

This CLI tool provides features to make building operators with [javaoperatorsdk](https://github.com/java-operator-sdk/java-operator-sdk) even easier.

## How to use

The project is currently under active development, hence you need to build from the code. The native & jar binaries will be provided as soon as the features are stable enough to release the first version.

### How to build

```
mvn clean package
```

### How to run

Running the following command will show what commands are already supported by the CLI.
```
java -jar target/jops.jar 
```

To find out what options a command supports:
```
java -jar target/jops.jar init -h
```

### Initialize operator example

Running the following command will generate a new *pure java* operator in `./out` that is using *Apache Maven* as build tool.

```
java -jar target/jops.jar init -b maven -f none -o ./out
```
