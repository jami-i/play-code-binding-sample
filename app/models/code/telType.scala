package models.code

abstract class TelType(val value:Int, val label:String) extends LabeledCode[Int]

object TelType extends NonNullCodes[Int, TelType]{
  override val name: String = "tel_type"
  override val parser: (String) => Int = _.toInt

  case object FixedLine extends TelType(1, "Fixed Line")
  case object Mobile    extends TelType(2, "Mobile")

  override val values: Set[TelType] = Set(FixedLine, Mobile)
}