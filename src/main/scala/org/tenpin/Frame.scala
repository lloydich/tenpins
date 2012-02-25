package org.tenpin

class Frame(ballOne: Int, ballTwo: Int) {

  def getScore() = {
     ballOne + ballTwo
  }
  def isAStrike() = {
     ballOne==10
  }

  def isASpare() = {
    getScore==10
  }

  def isFrameInPlay() = {
       !isAStrike() && ballTwo == null;
   }
}
