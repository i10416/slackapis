package dev.i10416.slackapis.http
object endpoints {
  val conversations = "https://slack.com/api/conversations"
  val conversationsInfo = s"$conversations.info"
  val setTopic = s"$conversations.setTopic"
}
