package dev.i10416.slackapis
import io.circe.generic.semiauto._
import dev.i10416.slackapis.http.res
import dev.i10416.slackapis.http.req
import io.circe.Encoder
import io.circe.Json
import io.circe.Decoder
import io.circe.HCursor
trait SlackCodec {

  implicit val styleDecoder: Decoder[Style] =
    new Decoder[Style] {
      def apply(c: HCursor): Decoder.Result[Style] = for {
        style0 <- c.field("style").as[String]
        style <- Style
          .fromString(style0)
          .toRight(
            io.circe.DecodingFailure
              .apply(s"invalid style: $style0", c.history)
          )
      } yield style
    }
  implicit val styleEnc: Encoder[Style] = new Encoder[Style] {
    def apply(a: Style): Json =
      a match {
        case Style.Default => Json.fromString("default")
        case Style.Danger  => Json.fromString("danger")
        case Style.Primary => Json.fromString("primary")
      }
  }

  implicit val actionTypeEnc: Encoder[ActionType] = new Encoder[ActionType] {
    def apply(a: ActionType): Json =
      a match {
        case ActionType.Button => Json.fromString("button")
      }
  }
  implicit val actionTypeDecoder: Decoder[ActionType] =
    new Decoder[ActionType] {
      def apply(c: HCursor): Decoder.Result[ActionType] = for {
        actionType <- c.field("type").as[String]
        actionTpe <- ActionType
          .fromString(actionType)
          .toRight(
            io.circe.DecodingFailure
              .apply(s"invalid action type: $actionType", c.history)
          )
      } yield actionTpe
    }
  implicit val attachmentTypeEnc: Encoder[AttachmentType] =
    new Encoder[AttachmentType] {
      def apply(a: AttachmentType): Json =
        a match {
          case AttachmentType.Default => Json.fromString("default")
        }
    }
  implicit val attachmentTypeDecoder: Decoder[AttachmentType] =
    new Decoder[AttachmentType] {
      def apply(c: HCursor): Decoder.Result[AttachmentType] = for {
        aType <- c.field("type").as[String]
        aTpe <- AttachmentType
          .fromString(aType)
          .toRight(
            io.circe.DecodingFailure
              .apply(s"invalid attachment type: $aType", c.history)
          )
      } yield aTpe
    }
  implicit val confirmCodec = deriveCodec[Confirm]
  implicit val userCodec = deriveCodec[User]
  implicit val teamCodec = deriveCodec[Team]
  implicit val actionCodec = deriveCodec[Action]
  implicit val attachmentCodec = deriveCodec[Attachment]
  implicit val msgCodec = deriveCodec[Message]
  implicit val topicCodec = deriveCodec[Topic]
  implicit val chCodec = deriveCodec[Channel]
  implicit val listChResCodec = deriveCodec[res.ListChannelsResponse]
  implicit val inviteBotToChannelRequestCodec =
    deriveCodec[req.InviteBotToChannelRequest]
}
