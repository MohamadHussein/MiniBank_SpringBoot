# Spring Boot Mini Bank

A simple web application that mimics a bank, where you can create customers, open accoutns for them and make transactions on these accounts.

## Getting Started

You may start by cloning the project up and running it on your local machine for development and testing purposes.

### Prerequisites



- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven ](https://maven.apache.org)

### Running the application


There are several options to run the application:

1- Using Maven:

```
mvn clean package
java -jar target/MiniBank-0.0.1-SNAPSHOT.jar
```

2. Using Docker

   1. clone the repo
   2. navigate to the project directory
   3. ```docker build -t mini-bank . ```
   4. ```docker run --rm -it -p 8080:8080```

## Testing the endpoints


[![Run in Postman](https://run.pstmn.io/button.svg)](https://www.getpostman.com/collections/09f32f37e27139f05295)


## Running the tests


Tests were written using Mockito, SpringTestMVC and Junit.

To run those tests do:
```
mvn clean test
```


[comment]: <> (## Deployment)


[comment]: <> (work in progress)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management




## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
