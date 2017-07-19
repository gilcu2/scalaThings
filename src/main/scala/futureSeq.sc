import scala.util.Random
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

def getList(begin:Int)=Seq.fill(3)(begin+Random.nextInt(3))

def getListFuture(begin:Int):Future[Seq[Int]]=Future {
  Thread.sleep(3000)
  Seq.fill(3)(begin+Random.nextInt(3))
}

class PrintActor extends Actor {
  def receive = {
    case a:Seq[(Int, Int)] => println(a)
    case _       => println("huh?")
  }
}

val system = ActorSystem("HelloSystem")
// default Actor constructor
val helloActor = system.actorOf(Props[PrintActor], name = "helloactor")

val pairListFuture: Seq[Future[Seq[(Int, Int)]]] =for(i <- Seq(1,4,7); l1 = getList(i))
  yield   getListFuture(i).map(l=>l.zip(l1))

