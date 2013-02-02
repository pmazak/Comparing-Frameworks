import org.squeryl.adapters.{H2Adapter, PostgreSqlAdapter}
import org.squeryl.internals.DatabaseAdapter
import org.squeryl.{Session, SessionFactory}
import play.api.db.DB
import play.api.GlobalSettings
import org.squeryl.PrimitiveTypeMode._
import play.api.Application
import models._

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    SessionFactory.concreteFactory = app.configuration.getString("db.default.driver") match {
      case Some("org.h2.Driver") => Some(() => getSession(new H2Adapter, app))
      case _ => sys.error("Database driver must be org.h2.Driver")
    }
    transaction {
      AppDB.create
      println("Created the schema")
    }
  }

  def getSession(adapter:DatabaseAdapter, app: Application) = Session.create(DB.getConnection()(app), adapter)

}