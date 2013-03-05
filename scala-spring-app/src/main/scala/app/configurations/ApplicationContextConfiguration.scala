package app.configurations

import org.apache.commons.dbcp.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.squeryl.PrimitiveTypeMode.transaction
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.squeryl.adapters.H2Adapter

import app.models.AppDb
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
class ApplicationContextConfiguration {

  @Bean
  def dataSource: DataSource = {
	val dataSource = new BasicDataSource
	dataSource.setDriverClassName("org.h2.Driver")
	dataSource.setUrl("jdbc:h2:file:db/development")
	dataSource
  }
 
  @PostConstruct
  def initAppDb = {
    SessionFactory.concreteFactory = Some(()=>
       Session.create(dataSource.getConnection(), new H2Adapter))
	try {
	  transaction {
	     AppDb.create
	     println("Created schema")
	  }
	}catch {
	    case tableExists: Exception => println("Existing table")
	}
  }
  
}