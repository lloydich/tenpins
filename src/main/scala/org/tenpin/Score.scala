package org.tenpin

import grizzled.slf4j.Logging

object Score extends Logging {

  case class Frame(frameNumber:Int, balls:List[Int])

  def createFrameScores(ballScores: List[List[Int]]) = {
    for (frameNo <- ballScores.size to 1 by -1) yield createFrameScore(frameNo, ballScores)
  }

  private def createFrameScore(frameNumber: Int, ballScores: List[List[Int]]): Option[Int] = {
    val frameBallScores: List[Int] = ballScores(frameNumber-1)

    val awaitingScoreFromFutureFrame: Boolean = awaitingScore(Frame(frameNumber,frameBallScores))
    val frameFinished: Boolean = !FrameScore.isInPlay(Frame(frameNumber,frameBallScores))

    if (frameFinished) {
      if (awaitingScoreFromFutureFrame) {
        val scoresFromFutureFrames: Option[Int] = getScoresFutureFrames(frameNumber, ballScores)
        if (scoresFromFutureFrames != None) Some(FrameScore.getCurrentScore(frameBallScores) + scoresFromFutureFrames.getOrElse(0))
        else None
      }
      else Some(FrameScore.getCurrentScore(frameBallScores))
    }
    else None
  }


   private def awaitingScore(frame:Frame): Boolean = frame match{
      case Frame (10,_) => false
      case Frame (_,_) => FrameScore.scoredTen(frame.balls)
    }

   private def getScoresFutureFrames(frameNumber: Int, ballScores: List[List[Int]]): Option[Int] = {

    info("ballScores.size:" + ballScores.size + ", frameNumber:" + frameNumber)
    if (ballScores.size > frameNumber ) {
      val currentFrame:Frame = Frame(frameNumber, ballScores(frameNumber-1))
      val nextFrame: Frame =  Frame(frameNumber+1, ballScores(frameNumber))

      calculateFutureScores(currentFrame, nextFrame, ballScores)

    }
    else {
      info("getScoresFromFutureFrames() no frames in the future")
      None
    }
  }

 private def calculateFutureScores(currentFrame:Frame,  nextFrame:Frame, ballScores: List[List[Int]]):Option[Int] =

   currentFrame match {

     case Frame(_, List(10, _*)) =>
       if (nextFrame.balls.size == 2) Some(FrameScore.getCurrentScore(nextFrame.balls))
       else if (nextFrame.balls.size == 1 && (((ballScores.size) - currentFrame.frameNumber) > 1)) {
         info("ballScores.size:" + ballScores.size + " , currentFrame.frameNumber:" + currentFrame.frameNumber)
         val nextNextFrame: Frame = Frame(currentFrame.frameNumber + 2, ballScores(currentFrame.frameNumber + 1))
         Some(nextFrame.balls(0) + (nextNextFrame.balls(0)))
       }
       else if(nextFrame.balls.size == 3) Some(nextFrame.balls(0)+nextFrame.balls(1))
       else None

     case Frame(_, List(_, _)) if (FrameScore.isASpare(currentFrame)) =>
       Some(nextFrame.balls(0))

     case _ =>
       error("getScoresFromFutureFrames() not a strike or a spare")
       throw new Exception("getScoresFromFutureFrames() not a strike or a spare");
   }


  def createNewBallScores(ballScore: Int, ballScores: List[List[Int]]): List[List[Int]] =  {
    checkScore(10,List(ballScore))
    if (ballScores.isEmpty) {
      ballScores ::: List(List(ballScore))
    }
    else if (!FrameScore.isInPlay(Frame(ballScores.size, ballScores.last))) {
      ballScores ::: List(List(ballScore))
    }
    else {

      checkScore(Frame(ballScores.size, (ballScores.last ::: List(ballScore))))
      ballScores.dropRight(1) ::: List(ballScores.last ::: List(ballScore))
    }
  }

  private def checkScore(frame: Frame):Unit = frame match {
    case Frame(10, _) if (frame.balls.size == 2) => checkScore(20, frame.balls)
    case Frame(10, _) if (frame.balls.size == 3) => checkScore(30, frame.balls)
    case Frame(_, _) => checkScore(10, frame.balls)
  }

 private def checkScore(max:Int, balls:List[Int]) {
    if (balls.reduceLeft[Int](_ + _) > max) throw new IllegalArgumentException("Score more than " + max)
  }

}
