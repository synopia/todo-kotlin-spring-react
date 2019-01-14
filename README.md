Kotlin, React, Redux, SpringBoot Todo Application
===

* Gradle Multiplatform Project (Common, Client and Server)
* Common contains shared code (TodoItem)
* Server runs a Spring Boot Application with a single RestController to serve TodoItems
* Client uses kotlinFrontend Gradle Plugin to generate JS Code
  * Uses npm, Karma and Webpack (you need a local npm installation)
  * Client is a react application using redux to manage state transitions
  * Loading todos from server is done using kotlin coroutines inside a redux middleware
  
Run Webpack server for client
> ./gradlew -t run

Run SpringBoot Server manually from server