package dev.i10416.slackapis
import dev.i10416.slackapis.codec._
import io.circe.parser
import scala.util.Random
class CodecSuite extends munit.FunSuite {
  test("channel") {
    val channel = Channel("foo", "bar", topic = Some(Topic("hoge", "fuga")))
    val data = """|
                         |{
                         |  "id": "foo",
                         |  "name": "bar",
                         |  "is_channel": true,
                         |  "is_private": false,
                         |  "is_archived": false,
                         |  "is_shared": false,
                         |  "topic": {
                         |    "value": "hoge",
                         |    "creator": "fuga"
                         |  }
                         |}""".stripMargin
    val Right(decoded) = parser.parse(data).flatMap(_.as[Channel])
    assertEquals(decoded, channel)
  }
  test("user") {
    import dev.i10416.slackapis.codec._

    val user = User(
      "name",
      "id",
      deleted = false,
      is_bot = false,
      is_owner = false,
      is_admin = false
    )
    val data = """|
                      |{
                      |  "id": "id",
                      |  "name": "name",
                      |  "deleted": false,
                      |  "is_bot": false,
                      |  "is_owner": false,
                      |  "is_admin": false
                      |}
                      |   """.stripMargin
    val Right(decoded) = parser.parse(data).flatMap(_.as[User])
    assertEquals(decoded, user)

  }
  test("team") {
    val team = Team("id", "domain")
    val data = """|
                      |{
                      |  "id": "id",
                      |  "domain": "domain"
                      |}
                      |   """.stripMargin
    val Right(decoded) = parser.parse(data).flatMap(_.as[Team])
    assertEquals(decoded, team)
  }
  test("actionType") {
    val strlen = Random.nextInt(100)
    val len = Random.nextInt(100)
    val strs =
      Iterable.fill(len)(Random.alphanumeric.take(strlen).toList.mkString)
    val btn = "button"
    assertEquals(ActionType.fromString(btn), Some(ActionType.Button))
    strs.foreach { str =>
      val alwaysNone = if (str == "button") None else ActionType.fromString(str)
      assertEquals(alwaysNone, None)
    }
  }
  test("style") {

    val valid = Set(
      ("default", Style.Default),
      ("primary", Style.Primary),
      ("danger", Style.Danger)
    )
    valid.foreach { case (str, expected) =>
      assertEquals(Style.fromString(str), Some(expected))
    }
  }
}
