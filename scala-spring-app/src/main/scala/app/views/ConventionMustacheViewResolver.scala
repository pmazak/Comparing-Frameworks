package app.views

import java.io.FileNotFoundException

import org.springframework.web.servlet.view.AbstractUrlBasedView
import org.springframework.web.servlet.view.mustache.MustacheViewResolver

class ConventionMustacheViewResolver extends MustacheViewResolver {
  override def buildView(viewName: String): AbstractUrlBasedView = {
    var view: String = viewName;
    if (viewName.endsWith("/")) {
      view = viewName.dropRight(1)
    }
    try {
    	super.buildView(view);
    }
    catch {
      case fnf: FileNotFoundException =>
      	super.buildView(viewName+"/index");
    }
      
  }
}