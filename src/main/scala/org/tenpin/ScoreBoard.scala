package org.tenpin

import collection.immutable.LinearSeq


class ScoreBoard extends FrameScore {
  var gameScore: Int = 0
  def getScoreBoard(player: Player): String = {
    var scoreBoard: StringBuilder = new StringBuilder
    scoreBoard.append("\nPLAYER " + player.name)
    scoreBoard.append("\n___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________")
    scoreBoard.append("\n|    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    |")
    scoreBoard.append("\n| " + ballScore(player, 0, 1) + " | " + ballScore(player, 0, 2) + " | | " + ballScore(player, 1, 1) + " | " + ballScore(player, 1, 2) + " | | " + ballScore(player, 2, 1) + " | " + ballScore(player, 2, 2) + " | | " + ballScore(player, 3, 1) + " | " + ballScore(player, 3, 2) + " | | " + ballScore(player, 4, 1) + " | " + ballScore(player, 4, 2) + " | | " + ballScore(player, 5, 1) + " | " + ballScore(player, 5, 2) + " | | " + ballScore(player, 6, 1) + " | " + ballScore(player, 6, 2) + " | | " + ballScore(player, 7, 1) + " | " + ballScore(player, 7, 2) + " | | " + ballScore(player, 8, 1) + " | " + ballScore(player, 8, 2) + " | | " + ballScore(player, 9, 1) + " | " + ballScore(player, 9, 2) + " |")
    scoreBoard.append("\n|    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____|")
    scoreBoard.append("\n|         | |         | |         | |         | |         | |         | |         | |         | |         | |         |")
    scoreBoard.append("\n|   " + gameScore(0, player) + "   | |   " + gameScore(1, player) + "   | |   " + gameScore(2, player) + "   | |   " + gameScore(3, player) + "   | |   " + gameScore(4, player) + "   | |   " + gameScore(5, player) + "   | |   " + gameScore(6, player) + "   | |   " + gameScore(7, player) + "   | |   " + gameScore(8, player) + "   | |   " + gameScore(9, player) + "   |")
    scoreBoard.append("\n|_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________|\n\n")
    return scoreBoard.toString
  }

  private def ballScore(player: Player, frameNumber: Int, ballNumber: Int): String = {

    val frameBalls: LinearSeq[Int] = player.ballScores(frameNumber)
    val ballScore: Int = ballNumber match {
      case 1 => if (frameBalls.size>0) frameBalls(0) else 0
      case 2 => if (frameBalls.size>1) frameBalls(1) else 0
    }
    formatBallScore(frameNumber, ballNumber, ballScore, frameBalls)
  }

  private def formatBallScore(frameNumber: Int, ballNumber: Int, ballScore: Int, frameBalls: LinearSeq[Int]): String = {
    val score: String = ballNumber match {
      case 1 => ballScore.toString
      case 2 if (isAStrike(frameBalls)) => "X"
      case 2 if (isASpare(Frame(frameNumber, frameBalls))) => "/"
      case _ => ballScore.toString

    }
    String.format("%2s", score)
  }

  private def gameScore(frameNumber: Int, player: Player): String = {
    val frameScore: Option[Int] = player.frameScores(frameNumber)
    val score: Int = frameScore.getOrElse(0)
    gameScore += score
    String.format("%3s", gameScore.toString)
  }
}