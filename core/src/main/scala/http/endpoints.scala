package dev.i10416.slackapis.http
object endpoints {
  val teams = "https://slack.com/api/teams"
  val conversations = "https://slack.com/api/conversations"
  val users = "https://slack.com/api/users"
  val conversationsInfo = s"$conversations.info"
  val setTopic = s"$conversations.setTopic"
  val userList = s"$users.list"
}
