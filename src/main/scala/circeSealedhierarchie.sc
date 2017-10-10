import io.circe.{Encoder, Json}
import io.circe.syntax._
import io.circe.generic.auto._

sealed trait A

case class B(v: Int) extends A

case class C(v: String) extends A

implicit val encodeA: Encoder[A] = new Encoder[A] {
  override final def apply(a: A): Json = a match {
    case x: B => x.asJson
    case x: C => x.asJson
  }
}

val b: A = B(1)

val json = b.asJson


