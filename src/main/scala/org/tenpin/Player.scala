package org.tenpin

import grizzled.slf4j.Logging
import scala.Some._

class Player(val name:String) extends Logging {

  var frameScores: List[Option[Int]] = List[Option[Int]]()
  var ballScores: List[List[Int]] = List[List[Int]]()

  //TODO convert to seq for referring to references with interfaces

  def score(): Int = {
//    def getIntValue(score: Option[Int]): Int = {
//      score match {
//        case None => 0
//        case Some(value) =>  value
//      }
//    }
    //if (frameScores.isEmpty) 0 else frameScores.reduceLeft((a: Int, b: Option[Int]) => a + b.getOrElse (0))
   // if (frameScores.isEmpty) 0 else frameScores.flatMap(x => x).reduceLeft(_ + _)
   if (frameScores.isEmpty || frameScores.flatten.isEmpty) 0 else  frameScores.flatten.reduceLeft((a: Int, b: Int) => a + b)
  }


  def add(ballScore: Int):Boolean = {
    def createNewBallScores: List[List[Int]] = {
      checkMoreThanTen(List(ballScore))
      if (ballScores.isEmpty) {
        ballScores ::: List(List(ballScore))
      }
      else {
        if (!Frame.isInPlay(ballScores.last)) {
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
    def getScoresFutureFrames(frameNumber:Int): Option[Int]={
      info("ballScores.size:"+ballScores.size+", frameNumber:"+frameNumber+1)
      if (ballScores.size > frameNumber+1) {
        val currentFrame: List[Int] = ballScores(frameNumber)
        val nextFrame: List[Int] = ballScores(frameNumber + 1)
        if (Frame.isAStrike(currentFrame)) {
          if (nextFrame.size == 2) Some(Frame.getCurrentScore(nextFrame))
          else if (nextFrame.size == 1 && ((ballScores.size-frameNumber)>1)) {
            val nextNextFrame: List[Int] = ballScores(frameNumber + 2)
            Some(nextFrame(0)+(nextNextFrame(0)))
          }
          else None
        }
        else if (Frame.isASpare(currentFrame)) {
          Some(nextFrame(0))
        }
        else {
          error("getScoresFromFutureFrames() not a strike or a spare")
          throw new Exception ("getScoresFromFutureFrames() not a strike or a spare");
        }
      }
      else {
        info("getScoresFromFutureFrames() no frames in the future")
        None
      }
    }
    def createFrameScore(frameNumber:Int): Option[Int] = { //for each frame working backwards score and check future frame(S) if scored 10
      val frameBallScores:List[Int] = ballScores(frameNumber)
      val awaitingScoreFromFutureFrame: Boolean = Frame.scoredTen(frameBallScores)
      val frameFinished:Boolean = !Frame.isInPlay(frameBallScores)
      if (frameFinished)  {
        if(awaitingScoreFromFutureFrame) {
         val scoresFromFutureFrames: Option[Int]  = getScoresFutureFrames(frameNumber)
          if  (scoresFromFutureFrames!=None)    Some(Frame.getCurrentScore(frameBallScores)+scoresFromFutureFrames.getOrElse(0))
          else None
        }
        else Some(Frame.getCurrentScore(frameBallScores))
      }
      else  None
    }
    def createFrameScores() ={
      for (frameNo <- ballScores.size-1 to 0 by -1) yield createFrameScore(frameNo)
    }

    info("\nadding " + ballScore)
    ballScores = createNewBallScores
    val frameFinished:Boolean = !Frame.isInPlay(ballScores.last)
    if (frameFinished)  {
    frameScores = createFrameScores.toList.reverse
    }
    frameFinished
  }



}
