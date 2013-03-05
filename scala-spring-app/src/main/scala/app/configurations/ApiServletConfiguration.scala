package app.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader
import org.springframework.web.servlet.view.mustache.MustacheViewResolver

@Configuration
@ComponentScan(basePackages=Array("app.controllers"))
class ApiServletConfiguration {

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

//  @Bean 
//  def mappingJacksonJsonView: MappingJacksonJsonView = {
//    new MappingJacksonJsonView
//  }
//  @Bean
//  def contentNegotiatingViewResolver:ContentNegotiatingViewResolver = {
//    val contentNegotiatingViewResolver = new ContentNegotiatingViewResolver
//    contentNegotiatingViewResolver.setOrder(1)
//    val map = new HashMap[String, String]
//    map.put("json", MediaType.APPLICATION_JSON.toString)
//    contentNegotiatingViewResolver.setMediaTypes(map)
//    val list = new ArrayList[View]
//    list.add(mappingJacksonJsonView)
//    contentNegotiatingViewResolver.setDefaultViews(list)
//    contentNegotiatingViewResolver
//  }
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