package org.tenpin

import grizzled.slf4j.Logging
import collection.immutable.LinearSeq

class Game(val players:LinearSeq[Player]) extends Logging {
   def winner():LinearSeq[Player]={
      val maxScore:Int = players.map(_.score).max
      players.filter(player => player.score == maxScore)
   }

  def add(playerId:Int, ballScore:Int):Boolean= {
    debug("\nadd ballscore: " + ballScore + " for player: "+playerId)
    players(playerId).add(ballScore)

  }

  def score(playerId:Int):Int={
     players(playerId).score()
  }

  def scoreBoard ={
    for(player <- players) yield new ScoreBoard().getScoreBoard(player)
  }
}
