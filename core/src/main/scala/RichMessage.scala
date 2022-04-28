package dev.i10416.slackapis

case class Message(
    text: String,
    ts: String,
    bot_id: Option[String] = None,
    attachments: Seq[Attachment] = Nil
)

case class Attachment(
    title: String,
    actions: Seq[Action],
    fallback: Option[String] = None
)
case class Action(
    name: String,
    value: String = "",
    text: Option[String] = Some(""),
    `type`: Option[ActionType] = None,
    attachment_type: Option[AttachmentType] = Some(AttachmentType.Default),
    style: Option[Style] = Some(Style.Default),
    confirm: Option[Confirm] = None
)

sealed trait ActionType
object ActionType {
  case object Button extends ActionType
  def fromString(str: String): Option[ActionType] = str match {
    case "button" => Some(Button)
    case _        => None
  }
}

sealed trait AttachmentType
object AttachmentType {
  case object Default extends AttachmentType
  def fromString(str: String): Option[AttachmentType] = str match {
    case "default" => Some(Default)
    case _         => None
  }
}

sealed trait Style
object Style {
  case object Default extends Style
  case object Danger extends Style
  case object Primary extends Style
  def fromString(str: String): Option[Style] = str match {
    case "default" => Some(Default)
    case "danger"  => Some(Danger)
    case "primary" => Some(Primary)
    case _         => None
  }
}

case class Confirm(
    title: String,
    text: String,
    ok_text: String,
    dismiss_text: String
)
