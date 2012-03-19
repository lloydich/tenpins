package org.tenpin
object Frame {

  def getCurrentScore(balls:List[Int])= {
    if (!balls.isEmpty)  balls.reduceLeft[Int](_+_) else 0
  }

//  def isAStrike(balls:List[Int]) = {
//    balls(0) == 10
//  }
  def isASpare(balls:List[Int]) = {
    getCurrentScore(balls) == 10
  }

  def isInPlay(balls:List[Int]) = {
    !isAStrike(balls) && balls.size ==1;
  }

  def isAStrike(balls:List[Int]) =
    balls match {
     case List(10, _*) => true
     case _ => false
    }

  def scoredTen(balls: List[Int]) =
    balls match {
   case List(10, _*) => true
   case  List(a, b) if(a+b==10) => true
   case _ => false
  }
}
