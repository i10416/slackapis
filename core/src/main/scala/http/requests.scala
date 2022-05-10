package dev.i10416.slackapis.http

object req {
  case class ConversationInfoRequest(
      channel: String,
      token: Option[String] = None
  )
  case class ConversationsSetTopicRequest(
      channel: String,
      topic: String
  )
  case class InviteBotToChannelRequest(
      channel: String,
      users: Seq[String]
  )
}
