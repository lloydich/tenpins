package org.tenpin

import grizzled.slf4j.Logging
import scala.Some._

class Player(val name:String) extends Logging {

  var frameScores: List[Option[Int]] = List[Option[Int]]()
  var ballScores: List[List[Int]] = List[List[Int]]()

  //TODO convert to seq for referring to references with interfaces

  def score(): Int = {
   if (frameScores.isEmpty || frameScores.flatten.isEmpty) 0 else  frameScores.flatten.reduceLeft((a: Int, b: Int) => a + b)
  }


  def add(ballScore: Int):Boolean = {
    def createNewBallScores: List[List[Int]] = {
      checkMoreThanTen(List(ballScore))
      if (ballScores.isEmpty) {
        ballScores ::: List(List(ballScore))
      }
      else if (!Frame.isInPlay(ballScores.last)) {
          ballScores ::: List(List(ballScore))
      }
      else {
          checkMoreThanTen(ballScores.last ::: List(ballScore))
          ballScores.dropRight(1) ::: List(ballScores.last ::: List(ballScore))
        }
    }
    def checkMoreThanTen(ballScores: List[Int]) {
      if (ballScores.reduceLeft[Int](_+_) >10 ) throw new IllegalArgumentException("Score more than 10")
    }

   

    info("\nadding " + ballScore)
    ballScores = createNewBallScores
    val frameFinished:Boolean = !Frame.isInPlay(ballScores.last)
    if (frameFinished)  {
       frameScores = Score.createFrameScores(ballScores).toList.reverse
    }
    frameFinished
  }



}
