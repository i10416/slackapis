package dev.i10416.slackapis

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
)
