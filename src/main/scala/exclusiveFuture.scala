/**
  * Created by gilcu2 on 7/19/17.
  */

import scala.util.{Failure, Random, Success}
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props


class PrintActor extends Actor {
  def receive = {
    case a: Seq[(Int, Int)] => println("zip:" + a)
    case b => println(s"huh? $b")
  }
}

object Main extends App {

  def getList(begin: Int) = Seq.fill(3)(begin + Random.nextInt(3))

  def getListFuture(begin: Int): Future[Seq[Int]] = Future {
    Thread.sleep(3000)
    Seq.fill(3)(begin + Random.nextInt(3))
  }

  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val printActor = system.actorOf(Props[PrintActor])

  for (i <- Seq(1, 4, 7); l1 = getList(i)) {
    val futureZip = getListFuture(i).map(l => l.zip(l1))
    futureZip.onComplete {
      case Success(zip) => printActor ! zip
      case Failure(e) => printActor ! e
    }
  }
}
