import cats._
import cats.instances.all._
import cats.syntax.show._

sealed trait Json
case class JsObject(tuple: (String, Json)*) extends Json
case class JsArray(list:List[Json]) extends Json
case class JsString(value:String) extends Json
case class JsNumber(value:Int) extends Json
case object JsTrue extends Json
case object JsFalse extends Json
case object JsNull extends Json


implicit val jsonShow = new Show[Json] {
  override def show(json:Json):String=json match {
    case JsNull=>"null"
    case JsTrue=>"True"
    case JsFalse=>"False"
    case a:JsString=>a.value
    case a:JsNumber=>a.value.toString
    case a:JsArray=>a.list.map(show).mkString("[",",","]")
    case JsObject(sub @ _*)=>sub.map(t=>{
      val (k,v)=t
      s"""
         |"$k":${show(v)}
       """.stripMargin

    }).mkString("{",",","}")
  }
}

val myJson:Json=JsObject("a"->JsString("a"),"b"->JsNumber(1))

myJson.show