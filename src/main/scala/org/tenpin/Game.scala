package org.tenpin

import grizzled.slf4j.Logging

class Game(val players:List[Player]) extends Logging {
   def winner():List[Player]={
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
}
