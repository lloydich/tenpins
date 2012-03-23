package org.tenpin

import org.tenpin.Frame
import grizzled.slf4j.Logging

class Game(val teams:List[List[Player]]) extends Logging {
  val numberOfPlayers:Int =  if(!teams.isEmpty) teams.flatten.size else 0

  def winner():List[Player]={
      val maxScore:Int =  teams.flatten.map(_.score).max
      teams.flatten.filter(player => player.score == maxScore)
   }

  def add(teamId:Int, playerId:Int, ballScore:Int):Boolean= {
    info("\nadding " + ballScore + " for player: "+playerId +" in team: "+teamId)
    teams(teamId)(playerId).add(ballScore)

  }

//  def printFrame(){
//    playerFrames.foreach(frame=>frame.foreach(ballScore=>println(", "+ballScore)))
//  }

    
  //  val frame:Frame = new Frame(ballScore,4)
   // playerFrames:::List[Frame](frame)
 // }

  def score(teamId:Int, playerId:Int):Int={
     teams(teamId)(playerId).score()
  }
}

