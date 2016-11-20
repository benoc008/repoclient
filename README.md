# repoclient
Command line client for the repository handler rest service.

## Build

To build, you need Java 8 and maven.
```
mvn clean install
```

## Usage

You can run the application as any other jar file.
```
java -jar repoclient-1.0.jar
```

If you use the program with wrong arguments, it will print the correct usage.

To add repository:
```
java -jar repoclient-1.0.jar add [name] [creator] 
```
To delete repository:
```
java -jar repoclient-1.0.jar delte [name] 
```
To find repository:
```
java -jar repoclient-1.0.jar get [name] 
```
To get all the repositories with the access count greater than or equal to N:
```
java -jar repoclient-1.0.jar get-by-access-count [N] 
```
For example, to list all the repositories, use this with N=0.
```
java -jar repoclient-1.0.jar get-by-access-count 0
```







