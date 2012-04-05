package org.tenpin

import grizzled.slf4j.Logging
import org.tenpin.Frame
import collection.immutable.LinearSeq

object Score extends Logging with FrameScore{



  def createFrameScores(ballScores: LinearSeq[LinearSeq[Int]]) = {
    for (frameNo <- ballScores.size to 1 by -1) yield frameScore(frameNo, ballScores)
  }

  private def frameScore(frameNumber: Int, ballScores: LinearSeq[LinearSeq[Int]]): Option[Int] = {
    val frameBallScores: LinearSeq[Int] = ballScores(frameNumber-1)

    val awaitingScoreFromFutureFrame: Boolean = awaitingScore(Frame(frameNumber,frameBallScores))
    val frameFinished: Boolean = !isInPlay(Frame(frameNumber,frameBallScores))

    if (frameFinished) {
      if (awaitingScoreFromFutureFrame) {
        val scoresFromFutureFrames: Option[Int] = getScoresFutureFrames(frameNumber, ballScores)
        if (scoresFromFutureFrames != None) Some(currentScore(frameBallScores) + scoresFromFutureFrames.getOrElse(0))
        else None
      }
      else Some(currentScore(frameBallScores))
    }
    else None
  }

   private def getScoresFutureFrames(frameNumber: Int, ballScores: LinearSeq[LinearSeq[Int]]): Option[Int] = {
    debug("getScoresFutureFrames() ballScores.size:" + ballScores.size + ", frameNumber:" + frameNumber)
    if (ballScores.size > frameNumber ) {
      val currentFrame:Frame = Frame(frameNumber, ballScores(frameNumber-1))
      val nextFrame: Frame =  Frame(frameNumber+1, ballScores(frameNumber))

      calculateFutureScores(currentFrame, nextFrame, ballScores)

    }
    else {
      debug("getScoresFromFutureFrames() no frames in the future")
      None
    }
  }

 private def calculateFutureScores(currentFrame:Frame, nextFrame:Frame, ballScores: LinearSeq[LinearSeq[Int]]):Option[Int] =

   currentFrame match {

     case Frame(_, List(10, _*)) =>
       if (nextFrame.balls.size == 2) Some(currentScore(nextFrame.balls))
       else if (nextFrame.balls.size == 1 && (((ballScores.size) - currentFrame.frameNumber) > 1)) {
         debug("ballScores.size:" + ballScores.size + " , currentFrame.frameNumber:" + currentFrame.frameNumber)
         val nextNextFrame: Frame = Frame(currentFrame.frameNumber + 2, ballScores(currentFrame.frameNumber + 1))
         Some(nextFrame.balls(0) + (nextNextFrame.balls(0)))
       }
       else if(nextFrame.balls.size == 3) Some(nextFrame.balls(0)+nextFrame.balls(1))
       else None

     case Frame(_, List(_, _)) if (isASpare(currentFrame)) =>
       Some(nextFrame.balls(0))

     case _ =>
       error("getScoresFromFutureFrames() not a strike or a spare")
       throw new Exception("getScoresFromFutureFrames() not a strike or a spare");
   }


  def createNewBallScores(ballScore: Int, ballScores: LinearSeq[LinearSeq[Int]]): LinearSeq[LinearSeq[Int]] =  {
    checkScore(10,List(ballScore))
    if (ballScores.isEmpty) {
      ballScores.toList ::: List(List(ballScore))
    }
    else if (!isInPlay(Frame(ballScores.size, ballScores.last))) {
      ballScores.toList ::: List(List(ballScore))
    }
    else {
      checkScore(Frame(ballScores.size, (ballScores.last.toList ::: List(ballScore))))
      ballScores.dropRight(1).toList ::: List(ballScores.last.toList ::: List(ballScore))
    }
  }





}
