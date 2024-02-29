import akka.actor.{Actor, ActorSystem, Props}


case class Info(name: String, year: Int)
case class Stream(branch: String)
class HelloAkka extends Actor{
  override def receive: Actor.Receive = {
    case message: String => println(s"Hi I got your message..$message")
    case _ => println("Any message.....Error." )
      val childActor = context.actorOf(Props[ChildActor], "ChildClass")
      childActor! "999"
      childActor! 2
     childActor !  Info("GO TO CHANNEL TWO", 2)
      childActor ! Stream("I am in Second channel as per ur request!")
      childActor ! "XXX"
  }
}

// This is an example of Channel Switching in AKKA with its actors..
// Here When you switch to other channel then your head comes to another Receiving Channel, then with further
// message passing channel with provide the output..
class ChildActor extends Actor {

  override def receive: Receive = {
    case message: String => println(s"I am a child, got your message '$message'"+" "+self.path.name)
    case value: Int =>
      println(value)
      context.become(channelFirst)


  }
  def  channelFirst: Receive ={
    case Info(info, value) => println(s"CHANNEL FIRST.. with $info and $value")
      println("Now Switching to Channel TWO")
    context.become(channelSecond)
  }
  def channelSecond : Receive= {
    case Stream(branch) => println(s"CHANNEL SECOND with this - $branch")
    case _ => println("Please Handle with CARE!!")
  }
}



object AkkaRoot extends App {

// creating an actor-system..
  val actorSystem = ActorSystem("ActorSystem")

    // creating an actor using actor system..
  val firstActor = actorSystem.actorOf(Props[HelloAkka], "RootActor")
  firstActor ! "Hi! How are you??"
  firstActor ! 121
}
















// Define an actor with multiple behaviors
class MyActor extends Actor {
  def receive: Receive = normalBehavior

  // Define the normal behavior
  def normalBehavior: Receive = {
    case "change" =>
      println("Switching to alternative behavior")
      context.become(alternativeBehavior) // Switch to alternative behavior
    case message =>
      println(s"Received message in normal behavior: $message")
  }

  // Define the alternative behavior
  def alternativeBehavior: Receive = {
    case "change" =>
      println("Switching back to normal behavior")
      context.become(normalBehavior) // Switch back to normal behavior
    case message =>
      println(s"Received message in alternative behavior: $message")
  }
}

object ContextBecomeExample extends App {
  // Create an ActorSystem
  val system = ActorSystem("ContextBecomeExampleSystem")

  // Create an instance of MyActor
  val myActor = system.actorOf(Props[MyActor], "myActor")

  // Send some messages to the actor
  myActor ! "Hello"
  myActor ! "change"
  myActor ! "Hi"
  myActor ! "change"
  myActor ! "How are you?"

  // Shutdown the ActorSystem
  system.terminate()
}


