package app.configurations

import java.util.ArrayList
import java.util.Properties

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean
import org.springframework.web.servlet.View
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import org.springframework.web.servlet.view.json.MappingJacksonJsonView
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader
import org.springframework.web.servlet.view.mustache.MustacheViewResolver

@Configuration
@ComponentScan(basePackages=Array("app.controllers"))    
class WebMvcConfiguration {

//  @Bean
//  def handlerMapping: RouterHandlerMapping = {
//    val handlerMapping = new RouterHandlerMapping
//    val routeFiles:List[String] = new ArrayList[String]
//    routeFiles.add("WEB-INF/classes/routes.conf")
//    handlerMapping.setRouteFiles(routeFiles)
//    handlerMapping.setAutoReloadEnabled(true)
//    handlerMapping
//  }
  
  @Bean
  def mustacheTemplateLoader: MustacheTemplateLoader = {
    new MustacheTemplateLoader
  }
  
  @Bean
  def mustacheViewResolver: MustacheViewResolver = {
    val mustacheViewResolver = new MustacheViewResolver
    mustacheViewResolver.setOrder(1)
    mustacheViewResolver.setCache(false)
    mustacheViewResolver.setPrefix("/WEB-INF/views/mustache/")
    mustacheViewResolver.setSuffix(".html")
    mustacheViewResolver.setTemplateLoader(mustacheTemplateLoader)
    mustacheViewResolver
  }
  
  @Bean
  def jstlViewResolver: InternalResourceViewResolver = {
    val internalResourceViewResolver = new InternalResourceViewResolver
    internalResourceViewResolver.setOrder(2)
    internalResourceViewResolver.setViewClass(classOf[JstlView])
    internalResourceViewResolver.setPrefix("/WEB-INF/views/jsp/")
    internalResourceViewResolver.setSuffix(".jsp")
    internalResourceViewResolver
  }  

  @Bean 
  def mappingJacksonJsonView: MappingJacksonJsonView = {
    new MappingJacksonJsonView
  }
  @Bean
  def contentNegotiatingViewResolver:ContentNegotiatingViewResolver = {
    val contentNegotiatingViewResolver = new ContentNegotiatingViewResolver
    val manager = new ContentNegotiationManagerFactoryBean
    manager.setFavorPathExtension(false)
    manager.setFavorParameter(true)
    val medias = new Properties
    medias.setProperty("html", "text/html")
    medias.setProperty("json", "application/json")
    manager.setMediaTypes(medias)
    contentNegotiatingViewResolver.setContentNegotiationManager(manager.getObject)
    val resolvers = new ArrayList[ViewResolver]
    resolvers.add(mustacheViewResolver)
    contentNegotiatingViewResolver.setViewResolvers(resolvers)
    val defaults = new ArrayList[View]
    defaults.add(mappingJacksonJsonView)
    contentNegotiatingViewResolver.setDefaultViews(defaults)
    contentNegotiatingViewResolver
  }
//  @Bean
//  def scalateViewResolver: ScalateViewResolver = {
//    val scalateViewResolver = new ScalateViewResolver
//    scalateViewResolver.setOrder(1)
//    scalateViewResolver.setViewClass(classOf[ScalateView])
//    scalateViewResolver.setPrefix("/WEB-INF/views/scalate/")
//    scalateViewResolver.setSuffix(".scaml")
//    scalateViewResolver
//  }  


}