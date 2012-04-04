package org.tenpin

import org.tenpin.Score.Frame


object FrameScore {

  def getCurrentScore(balls:List[Int])= {
    if (!balls.isEmpty)  balls.reduceLeft[Int](_+_) else 0
  }

  def isASpare(frame:Frame):Boolean = frame match{
     case Frame(10,_) =>   frame.balls.size==2 &&  getCurrentScore(frame.balls)==10
     case Frame(_,_) =>   getCurrentScore(frame.balls) == 10

  }

  def isInPlay(frame:Frame):Boolean = frame match {
      case Frame(10,_) =>    (frame.balls.size ==1) || ((isAStrike(frame.balls) || isASpare(frame)) && frame.balls.size==2);
      case Frame(_,_) => (!isAStrike(frame.balls) && (frame.balls.size ==1));
  }


  def isAStrike(balls:List[Int]):Boolean =
    balls match {
     case List(10, _*) => true
     case _ => false
    }

  def scoredTen(balls: List[Int]):Boolean =
    balls match {
   case List(10, _*) => true
   case List(a, b) if(a+b==10) => true
   case _ => false
  }
}
