package dev.i10416.slackapis.http

import io.circe.Encoder
import io.circe.Decoder
import cats.effect.kernel.Async
import org.http4s.ember.client._
import org.http4s.circe._
import org.http4s.EntityEncoder
import org.http4s.EntityDecoder
import org.http4s.dsl.io._
import org.http4s.Uri
import org.http4s.Request
import org.http4s.headers.{`Content-Type`}
import org.http4s.{Method, MediaType}
import Method.{GET, POST}
import MediaType.application

abstract class JsonAPIClient[F[_]: Async] {

  lazy protected val client = EmberClientBuilder.default[F].build

  def get[Res: Decoder](requestBuilder: Request[F] => Request[F]): F[Res] = {
    implicit val responseDecoder: EntityDecoder[F, Res] = jsonOf[F, Res]

    client.use { client =>
      val baseRequest = Request[F](Method.GET)
      client.expect[Res](requestBuilder(baseRequest))
    }
  }

  def post[Req: Encoder, Res: Decoder](
      requestEntity: Req
  )(requestBuilder: Request[F] => Request[F]): F[Res] = {
    implicit val reqestEncoder: EntityEncoder.Pure[Req] = jsonEncoderOf[Req]
    implicit val responseDecoder: EntityDecoder[F, Res] = jsonOf[F, Res]

    client.use { client =>
      val baseRequest = Request[F](Method.POST)
        .withHeaders(
          `Content-Type`(application.json)
        )
        .withEntity(requestEntity)
      client.expect[Res](requestBuilder(baseRequest))
    }
  }

}

object JsonAPIClient {
  def get[F[_]: Async, Res: Decoder](
      requestBuilder: Request[F] => Request[F]
  ): F[Res] = {
    implicit val responseDecoder: EntityDecoder[F, Res] = jsonOf[F, Res]

    EmberClientBuilder.default[F].build.use { client =>
      val baseRequest = Request
        .apply[F](Method.GET)
      client.expect[Res](requestBuilder(baseRequest))
    }
  }
  def post[F[_]: Async, Req: Encoder, Res: Decoder](
      requestEntity: Req
  )(requestBuilder: Request[F] => Request[F]): F[Res] = {
    implicit val reqestEncoder: EntityEncoder.Pure[Req] = jsonEncoderOf[Req]
    implicit val responseDecoder: EntityDecoder[F, Res] = jsonOf[F, Res]

    EmberClientBuilder.default[F].build.use { client =>
      val baseRequest = Request
        .apply[F](Method.POST)
        .withHeaders(
          `Content-Type`(application.json)
        )
        .withEntity(requestEntity)
      client.expect[Res](requestBuilder(baseRequest))
    }
  }
}
