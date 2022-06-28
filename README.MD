#Test assignment
###Task description
Implement REST service to query open data set for amount of students in all colleges of region.

#####Provided input
POST HTTP call with provided JSON payload
```aidl
{
"criteria": {
    "byGrades" : "true",
    "byName" : "false"
    }
}
```
#####Required output 
JSON response with OK 200 HTTP status and payload
```aidl
{
"students": {
    "gradedA" : 10,
    "gradedB" : 15,
    "notGraded" : 1
    }
}
```
###Implementation assumptions
1. Incoming data uses http
2. Mapping is done on ID basis
3. ...

###Compile, run tests and execute
Please use gradle wrapper to compile and run application from command line 

#####Run tests
Use the command below to run tests from console
```aidl
./gradlew --console=plain clean build
```   

#####Run application
Use command below to start the application
```aidl
./gradlew --console=plain bootRun
```

###API Documentation
After application start , there is swagger documentation enabled for this project

1. Swagger2 GUI with documentation http://localhost:8080/swagger-ui.html

2. Swagger2 auto-generated API is available by url http://localhost:8080/v2/api-docs