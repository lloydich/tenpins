package org.tenpin

import grizzled.slf4j.Logging

object Score extends Logging {

    private  def getScoresFutureFrames(frameNumber: Int, ballScores: List[List[Int]]): Option[Int] = {
    //TODO move this to case classes
    info("ballScores.size:" + ballScores.size + ", frameNumber:" + frameNumber + 1)
    if (ballScores.size > frameNumber + 1) {
      val currentFrame: List[Int] = ballScores(frameNumber)
      val nextFrame: List[Int] = ballScores(frameNumber + 1)
      if (Frame.isAStrike(currentFrame)) {
        if (nextFrame.size == 2) Some(Frame.getCurrentScore(nextFrame))
        else if (nextFrame.size == 1 && ((ballScores.size - frameNumber) > 1)) {
          val nextNextFrame: List[Int] = ballScores(frameNumber + 2)
          Some(nextFrame(0) + (nextNextFrame(0)))
        }
        else None
      }
      else if (Frame.isASpare(currentFrame)) {
        Some(nextFrame(0))
      }
      else {
        error("getScoresFromFutureFrames() not a strike or a spare")
        throw new Exception("getScoresFromFutureFrames() not a strike or a spare");
      }
    }
    else {
      info("getScoresFromFutureFrames() no frames in the future")
      None
    }
  }

  private def createFrameScore(frameNumber: Int, ballScores: List[List[Int]]): Option[Int] = {
    val frameBallScores: List[Int] = ballScores(frameNumber)
    val awaitingScoreFromFutureFrame: Boolean = Frame.scoredTen(frameBallScores)
    val frameFinished: Boolean = !Frame.isInPlay(frameBallScores)
    if (frameFinished) {
      if (awaitingScoreFromFutureFrame) {
        val scoresFromFutureFrames: Option[Int] = getScoresFutureFrames(frameNumber, ballScores)
        if (scoresFromFutureFrames != None) Some(Frame.getCurrentScore(frameBallScores) + scoresFromFutureFrames.getOrElse(0))
        else None
      }
      else Some(Frame.getCurrentScore(frameBallScores))
    }
    else None
  }

  def createFrameScores(ballScores: List[List[Int]]) = {
    for (frameNo <- ballScores.size - 1 to 0 by -1) yield createFrameScore(frameNo, ballScores)
  }

}
