package dev.i10416.slackapis

case class Channel(
    id: String,
    name: String,
    is_channel: Option[Boolean] = Some(true),
    is_private: Option[Boolean] = Some(false),
    is_archived: Option[Boolean] = Some(false),
    is_shared: Option[Boolean] = Some(false),
    topic: Option[Topic] = None,
    num_members: Option[Int] = None
)

case class Topic(
    value: String,
    creator: String
)
