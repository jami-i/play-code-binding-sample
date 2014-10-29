package models.code

import javax.swing.text.DateFormatter

import play.api.data.FormError
import play.api.data.format.Formatter

trait OptionValue {
  def toOption:(String, String)
}

trait Code[V] extends OptionValue{
  val value: V
  override def toOption = (value.toString, value.toString)
}

trait Labeled {
  val label: String
}

trait LabeledCode[V] extends Code[V] with Labeled {
  override def toOption = (value.toString, label)
}

trait Codes[V, T <: Code[V]] {
  val name: String
  val values: Set[T]
  val parser:(String => V)

  val formatter:Formatter[T]

}

trait NonNullCodes[V, T <: Code[V]] extends Codes[V, T] {
  def apply(value: V):T = values.find( _.value == value).get

  override val formatter: Formatter[T] = new Formatter[T]{
    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], T] = {
      import scala.util.control.Exception._
      data.get(key) match {
        case Some(k) =>
          allCatch.either{ apply(parser(k)) }
          .left.map(t => Seq(new FormError(key, s"format.$name", Nil)))
        case None    => Left(Seq(new FormError(key, s"format.$name", Nil)))
      }
    }

    override def unbind(key: String, value: T): Map[String, String] = Map(key -> value.value.toString)
  }
}

trait NullableCodes[V, T <: Code[V]] extends Codes[V, T] {
  def apply(value: V):Option[T] = values.find(_.value == value)
}
