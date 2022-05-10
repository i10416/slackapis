package dev.i10416.slackapis.http
import dev.i10416.slackapis._
object res {

  case class ConversationInfoResponse(
      ok: Boolean,
      channel: Option[Channel],
      error: Option[String] = None
  )
  case class ConversationsSetTopicResponse(
      ok: Boolean,
      channel: Option[Channel],
      error: Option[String] = None
  )
  case class ListChannelsResponse(
      ok: Boolean,
      channels: Seq[Channel],
      error: Option[String] = None
  )
  case class TeamAccessLogsResponse(
      ok: Boolean,
      logins: Option[Seq[_]],
      error: Option[String] = None
  )
}
