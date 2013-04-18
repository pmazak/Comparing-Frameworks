package app

import org.scalatra.LifeCycle
import javax.servlet.ServletContext

class Scalatra extends LifeCycle {

  override def init(context: ServletContext) {

    // mount servlets like this:
    context mount (new YourServlet, "/your/*")

    // set init params like this:
    context.initParameters("org.scalatra.environment") = "production"
    context.initParameters("org.scalatra.cors.allowedOrigins") = "http://example.com"
  }
}