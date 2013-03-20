You'll want this particular Scala IDE eclipse update site: http://download.scala-ide.org/sdk/e38/scala210/stable/site (works with Scala 2.10)

For Play: cd play-scala-app; play run; 
http://localhost:9000/  &  http://localhost:9000/tasks

For Scala-Spring: cd scala-spring-app; gradlew war jettyRun; 
http://localhost:8080/ & http://localhost:8080/api/task/list
(supposedly gradle doesn't need to be installed with this wrapper)
