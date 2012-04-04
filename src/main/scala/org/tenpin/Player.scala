package org.tenpin

import grizzled.slf4j.Logging
import scala.Some._
import org.tenpin.Score.Frame

class Player(val name:String) extends Logging {

  var frameScores: List[Option[Int]] = List[Option[Int]]()
  var ballScores: List[List[Int]] = List[List[Int]]()

  //TODO convert to seq for referring to references with interfaces

  def score(): Int = {
   if (frameScores.isEmpty || frameScores.flatten.isEmpty) 0 else  frameScores.flatten.reduceLeft((a: Int, b: Int) => a + b)
  }


  def add(ballScore: Int):Boolean = {
    info("\nadding " + ballScore)
    ballScores = Score.createNewBallScores(ballScore, ballScores)
    info("\n frame no:"+ballScores.size)
    val frameFinished:Boolean = !FrameScore.isInPlay(Frame(ballScores.size, ballScores.last))
    if (frameFinished)  {
       frameScores = Score.createFrameScores(ballScores).toList.reverse
    }
    frameFinished
  }

}
