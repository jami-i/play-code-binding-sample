package models.code

sealed abstract class Status(val value:String, val label:String)
extends LabeledCode[String]

object Status extends NonNullCodes[String, Status]{

  override val name = "status"

  case object Attend   extends Status("atd","Attend")
  case object Vacation extends Status("off","Vacation")
  case object Late     extends Status("lte","Late")

  override val values: Set[Status] = Set(Attend, Vacation, Late)
  override val parser: (String) => String = s => s
}