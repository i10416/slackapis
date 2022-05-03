package dev.i10416.slackapis

case class User(
    name: String,
    id: String,
    deleted: Boolean = false,
    is_bot: Boolean = false,
    is_owner: Boolean = false,
    is_admin: Boolean = false,
    // multi channels guest
    is_restricted: Option[Boolean] = None,
    // single channel guest
    is_ultra_restricted: Option[Boolean] = None
) {
  val SLACKBOT_ID = "USLACKBOT"
  def isActive: Boolean = !deleted & !is_bot & id != SLACKBOT_ID
  def isOwner: Boolean = isActive & is_owner
  def isAdmin: Boolean = isActive & is_admin
  def is_member: Boolean = isActive & !isAdmin & !isLicensed & !isFree
  def isLicensed: Boolean =
    isActive & !isFree & is_restricted.fold(false)(identity)
  def isFree: Boolean = isActive & is_ultra_restricted.fold(false)(identity)
}
