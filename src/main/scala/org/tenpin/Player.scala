package org.tenpin

import grizzled.slf4j.Logging

class Player(val name:String) extends Logging {

  var frameScores: List[Int] = List[Int]()
  var ballScores: List[List[Int]] = List[List[Int]]()

  def score(): Int = {
    if (frameScores.isEmpty) 0 else frameScores.reduceLeft((a: Int, b: Int) => a + b)
  }

 
  

  def add(ballScore: Int):Boolean = {
    
    info("\nadding " + ballScore)
    
    def createNewBallScores: List[List[Int]] = {
      checkMoreThanTen(List(ballScore))
      if (ballScores.isEmpty) {
        ballScores ::: List(List(ballScore))
      }
      else {
        if (!Frame.isFrameInPlay(ballScores.last)) {
          ballScores ::: List(List(ballScore))
        }
        else {
          checkMoreThanTen(ballScores.last ::: List(ballScore))
          ballScores.dropRight(1) ::: List(ballScores.last ::: List(ballScore))
        }
      }
    }
    def checkMoreThanTen(ballscores: List[Int]) {
      if (ballscores.reduceLeft[Int](_+_) >10 ) throw new IllegalArgumentException("Score more than 10")
    }

    ballScores = createNewBallScores
    val frameFinished:Boolean = !Frame.isFrameInPlay(ballScores.last)
    if (frameFinished) frameScores = frameScores ::: List(Frame.getCurrentScore(ballScores.last))
    frameFinished
  }



}
