package org.tenpin

import grizzled.slf4j.Logging

class Player extends Logging {

  var frameScores: List[Int] = List[Int]()
  var ballScores: List[List[Int]] = List[List[Int]]()


  def score(): Int = {
    if (frameScores.isEmpty) 0 else frameScores.reduceLeft((a: Int, b: Int) => a + b)
  }

  def add(ballScore: Int) {
    info("\nadding " + ballScore)

    def createNewBallScores: List[List[Int]] = {
      if (ballScores.isEmpty) {
        ballScores ::: List(List(ballScore))
      }
      else {
        if (!Frame.isFrameInPlay(ballScores.last)) {
          ballScores ::: List(List(ballScore))
        }
        else {
          ballScores.dropRight(1) ::: List(ballScores.last ::: List(ballScore))
        }
      }
    }

    ballScores = createNewBallScores

    if (!Frame.isFrameInPlay(ballScores.last))
      frameScores = frameScores ::: List(Frame.getCurrentScore(ballScores.last))
  }
}
