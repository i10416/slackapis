package dev.i10416.slackapis

import java.time.Instant

case class Team(id: String, domain: String)

case class AccessLog(
    user_id: String,
    username: String,
    date_first: Long,
    date_last: Long,
    count: Int,
    ip: String,
    user_agent: String,
    country: String,
    region: String
) {
  val dateLast = Instant.ofEpochSecond(date_last)
  val dateFirst = Instant.ofEpochSecond(date_first)
}
