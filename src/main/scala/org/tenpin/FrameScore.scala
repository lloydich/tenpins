package org.tenpin


case class Frame(frameNumber:Int, balls:List[Int])

trait FrameScore {

  def currentScore(balls:List[Int])= {
    if (!balls.isEmpty)  balls.reduceLeft[Int](_+_) else 0
  }

  def isASpare(frame:Frame):Boolean = frame match{
     case Frame(10,_) =>   frame.balls.size==2 &&  currentScore(frame.balls)==10
     case Frame(_,_) =>   currentScore(frame.balls) == 10

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

  def awaitingScore(frame:Frame): Boolean = frame match{
      case Frame (10,_) => false
      case Frame (_,_) => scoredTen(frame.balls)
    }

  def checkScore(frame: Frame):Unit = frame match {
    case Frame(10, _) if (frame.balls.size == 2) => checkScore(20, frame.balls)
    case Frame(10, _) if (frame.balls.size == 3) => checkScore(30, frame.balls)
    case Frame(_, _) => checkScore(10, frame.balls)
  }

  def checkScore(max:Int, balls:List[Int]) {
    if (balls.reduceLeft[Int](_ + _) > max) throw new IllegalArgumentException("Score more than " + max)
  }
}
