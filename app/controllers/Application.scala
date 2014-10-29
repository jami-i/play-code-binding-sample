package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  import play.api.data._
  import play.api.data.Forms._

  val form = Form(
   single(
     "status" -> of[models.code.Status](models.code.Status.formatter)
   )
  )

  def index = Action { implicit request =>

    form.bindFromRequest.fold(
      errors => println(errors),
      status => println(status)
    )

    Ok(views.html.index("Your new application is ready."))
  }

}