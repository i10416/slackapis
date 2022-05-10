package dev.i10416.slackapis
import dev.i10416.slackapis.http.res
import dev.i10416.slackapis.http.req
import io.circe.Encoder
import io.circe.Json
import io.circe.Decoder
import io.circe.HCursor
import io.circe.generic.semiauto._
import io.circe.Codec.AsObject

trait SlackCodec:

  given Decoder[Style] = new Decoder[Style]:
    def apply(c: HCursor): Decoder.Result[Style] = for
      style0 <- c.field("style").as[String]
      style <- Style
        .fromString(style0)
        .toRight(
          io.circe.DecodingFailure
            .apply(s"invalid style: $style0", c.history)
        )
    yield style
  given Encoder[Style] = new Encoder[Style]:
    def apply(a: Style): Json =
      a match
        case Style.Default => Json.fromString("default")
        case Style.Danger  => Json.fromString("danger")
        case Style.Primary => Json.fromString("primary")
  given Encoder[ActionType] = new Encoder[ActionType]:
    def apply(a: ActionType): Json = a match
      case ActionType.Button => Json.fromString("button")
  given Decoder[ActionType] = new Decoder[ActionType]:
    def apply(c: HCursor): Decoder.Result[ActionType] = for
      actionType <- c.field("type").as[String]
      actionTpe <- ActionType
        .fromString(actionType)
        .toRight(
          io.circe.DecodingFailure
            .apply(s"invalid action type: $actionType", c.history)
        )
    yield actionTpe

  given Encoder[AttachmentType] = new Encoder[AttachmentType]:
    def apply(a: AttachmentType): Json =
      a match
        case AttachmentType.Default => Json.fromString("default")
  given Decoder[AttachmentType] = new Decoder[AttachmentType]:
    def apply(c: HCursor): Decoder.Result[AttachmentType] = for
      aType <- c.field("type").as[String]
      aTpe <- AttachmentType
        .fromString(aType)
        .toRight(
          io.circe.DecodingFailure
            .apply(s"invalid attachment type: $aType", c.history)
        )
    yield aTpe
  implicit given AsObject[Action] = deriveCodec[Action]
  implicit given AsObject[User] = deriveCodec[User]
  implicit given AsObject[Team] = deriveCodec[Team]
  implicit given AsObject[AccessLog] = deriveCodec[AccessLog]
  implicit given AsObject[Attachment] = deriveCodec[Attachment]
  implicit given AsObject[Message] = deriveCodec[Message]
  implicit given AsObject[Confirm] = deriveCodec[Confirm]
  implicit given AsObject[Topic] = deriveCodec[Topic]
  implicit given AsObject[Channel] = deriveCodec[Channel]
  implicit given AsObject[res.ConversationInfoResponse] =
    deriveCodec[res.ConversationInfoResponse]
  implicit given AsObject[res.ConversationsSetTopicResponse] =
    deriveCodec[res.ConversationsSetTopicResponse]
  implicit given AsObject[res.ListChannelsResponse] =
    deriveCodec[res.ListChannelsResponse]
  implicit given AsObject[req.InviteBotToChannelRequest] =
    deriveCodec[req.InviteBotToChannelRequest]
