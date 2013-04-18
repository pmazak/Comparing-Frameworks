package app

import org.scalatra._

class YourServlet extends ScalatraServlet {
  get("/") {
    <h1>Hello, world!</h1>
  }
}