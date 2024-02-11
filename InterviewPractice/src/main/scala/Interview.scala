
object Interview extends App {
val answersFunc = new Questions

//  println(answersFunc.makeFirstWordCapital("hi ram i am there."))
//  println("The value given Palindrome - "+answersFunc.isItPalindrome("SAMAR"))
//  answersFunc.countDuplicateChar("AABBCCGNKFNDK")
//  answersFunc.fetchUniqueCharArray(Array('A','B','C','C','D','E','E'))
////  answersFunc.printFibonacciSeries(7)
//  answersFunc.sortintegerArray(Array(6,5,3))
  answersFunc.sortMyintegerArray(Array(7,3,2,1,3,5))
}

class Questions{
  // A -A -B - B C- (H)C  -A(T)
  def countDuplicateChar(value: String) ={
    val valueCollector: Array[Char] = new Array[Char](value.length)
    var count = 0
    for(head <- 0 until value.length){
      if(!valueCollector.contains(value.charAt(head))){
        for(tail <- (head + 1) until (value.length)){
          if(value.charAt(head) == value.charAt(tail)) {
            count += 1
          }
        }
        valueCollector(head) = value.charAt(head)
        if(count>0) {
          println("Char- " + value(head) + " is coming with duplication count " + (count))
          count = 0
        }
      }
    }
  }
  // (A-A-B-B-C-D-D-R-T-Y)
  // Count + Char Name:
  def fetchUniqueCharArray(valueArrayString: Array[Char]) : Unit ={
    var counter = 0
    val uniqueArrayOfChar = new Array[Char](valueArrayString.length)
     valueArrayString.foreach { char =>
      if(!uniqueArrayOfChar.contains(char)) {
       uniqueArrayOfChar(counter) = char
        counter += 1
      }
    }
   uniqueArrayOfChar.filter(_ !=' ').foreach(print)

  }
  // List(5(H),7,3,5,2,6,7) -> (ascending)
  def sortMyintegerArray(integerArray: Array[Int])={
    var temp = 0
    for(head <- integerArray.indices){
      for(tail <- (head + 1) until (integerArray.length)){
        if(integerArray(head) > integerArray(tail)){
          temp = integerArray(tail)
          integerArray(tail) = integerArray(head)
          integerArray(head) = temp
        }
      }
    }
    // .distinct in array is used to remove duplicated values from an array...
    integerArray.distinct.foreach(print)




  }












  // hi ram i am there.
  def makeFirstWordCapital(value: String)  = {
    value.split(' ').map(word=>word.capitalize).mkString(" ")
  }

  def isItPalindrome(value: String):Boolean ={
    val getResult = (for(counter <- value.length-1 to(0) by -1) yield value(counter)).mkString("")
    println(getResult)
    getResult == value
  }


  //(2,4,3,5,3,6,4) -- H -> 2 2-> 4, 3, 5, ....
  def sortintegerArray(list: Array[Int]) = {
    val sortedArray = Array[Int](list.length)

    for(count <- list.indices){
      for(innerCount <- 1 until  list.length-1){
        if(list(count)>list(innerCount)){
          sortedArray(count) = list(innerCount)
        }
      }
    }
    for(value<-sortedArray){
      println(value)
    }
  }

  def printFibonacciSeries(number: Int)= {
    val fibArray = new Array[Int](number+1)
     fibArray(0) = 0
     fibArray(1) = 1
    for(count<- 2 to number){
      fibArray(count) = fibArray(count-1) + fibArray(count-2)
    }
    println(fibArray.last)
}
}





