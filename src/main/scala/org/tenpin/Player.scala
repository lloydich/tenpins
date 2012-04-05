package org.tenpin

import grizzled.slf4j.Logging
import scala.Some._
import collection.immutable.LinearSeq


class Player(val name:String) extends Logging with FrameScore {

  var frameScores: LinearSeq[Option[Int]] = List[Option[Int]]()
  var ballScores: LinearSeq[LinearSeq[Int]] = List[List[Int]]()



  //TODO convert to seq for referring to references with interfaces

  def score(): Int = {
   if (frameScores.isEmpty || frameScores.flatten.isEmpty) 0 else  frameScores.flatten.reduceLeft((a: Int, b: Int) => a + b)
  }


  def add(ballScore: Int):Boolean = {
    debug("\nadding " + ballScore)
    ballScores = Score.createNewBallScores(ballScore, ballScores)
    debug("\n frame no:"+ballScores.size)
    val frameFinished:Boolean = !isInPlay(Frame(ballScores.size, ballScores.last))
    if (frameFinished)  {
       frameScores = Score.createFrameScores(ballScores).toList.reverse
    }
    frameFinished
  }

}
