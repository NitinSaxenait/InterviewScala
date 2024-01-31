object Interview extends App {

  val startingPoint: String = "Hello World"
  def firstFunc(value: Int): Unit = {
    if(value == 0){
      println(startingPoint)
    }
    else
      println("The first scala program.")
  }

  firstFunc(1)


}
