package org.tenpin
object Frame {
  def getCurrentScore(balls:List[Int])= {
    if (!balls.isEmpty)  balls(0) + balls(1) else 0
  }

  def isAStrike(balls:List[Int]) = {
    balls(0) == 10
  }

  def isASpare(balls:List[Int]) = {
    getCurrentScore(balls) == 10
  }

  def isFrameInPlay(balls:List[Int]) = {
    !isAStrike(balls) && balls.size ==1;
  }
}
