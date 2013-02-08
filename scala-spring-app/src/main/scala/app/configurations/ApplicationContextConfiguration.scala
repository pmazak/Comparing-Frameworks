package app.configurations

import org.springframework.web.servlet.view.json.MappingJacksonJsonView
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Bean
import java.util.HashMap
import org.springframework.context.annotation.Configuration
import java.util.ArrayList
import org.springframework.web.servlet.view.JstlView
import org.springframework.web.servlet.View
import org.springframework.http.MediaType


@Configuration
@ComponentScan(basePackages=Array("app.controllers"))
class ApplicationContextConfiguration {

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
  @Bean
  def jstlViewResolver: InternalResourceViewResolver = {
    val internalResourceViewResolver = new InternalResourceViewResolver
    internalResourceViewResolver.setOrder(2)
    internalResourceViewResolver.setViewClass(classOf[JstlView])
    internalResourceViewResolver.setPrefix("/WEB-INF/views/")
    internalResourceViewResolver.setSuffix(".jsp")
    internalResourceViewResolver
  }  
}