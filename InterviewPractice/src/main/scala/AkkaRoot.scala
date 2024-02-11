import akka.actor.{Actor, ActorSystem, Props}


case class Data(info: String, value: Int)
case class Support(branch: String)
class HelloAkka extends Actor{
  override def receive: Actor.Receive = {
    case message: String => println(s"Hi I got your message..$message")
    case _ => println("Any message.....Error.")
      val childActor = context.actorOf(Props[ChildActor], "ChildClass")
      childActor! 999
      childActor! "I am child of Hello Akka"
  }
}

class ChildActor extends Actor {

  override def receive: Receive = {
    case message: String => println(s"Hi got ur message '$message'"+" "+self.path.name)
    case value: Int =>
      println(value)
      context.become(channelFirst)


  }
  def  channelFirst: Receive ={
    case Data(info, value) => println(s"We are Channel First with this.. with $info and $value")
    context.become(channelSecond)
  }
  def channelSecond : Receive= {
    case Support(branch) => println(s"We are Channel Second with this - $branch")
  }
}



object AkkaRoot extends App {

// creating an actor-system..
  val actorSystem = ActorSystem("ActorSystem")

    // creating an actor using actor system..
  val firstActor = actorSystem.actorOf(Props[HelloAkka], "RootActor")
  firstActor ! "Hi! How are you??"
  firstActor ! 121
  firstActor ! Data("GO TO CHANNEL TWO", 2)
  firstActor ! Support("I am in Second channel as per ur request!")




}


