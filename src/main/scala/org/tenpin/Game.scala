package org.tenpin

import org.tenpin.Frame
import grizzled.slf4j.Logging

class Game(val players:List[Player])   extends Logging {





  def add(playerId:Int, ballScore:Int):Boolean= {
    info("\nadding " + ballScore + " for player: "+playerId)
    players(playerId).add(ballScore)

  }

//  def printFrame(){
//    playerFrames.foreach(frame=>frame.foreach(ballScore=>println(", "+ballScore)))
//  }

    
  //  val frame:Frame = new Frame(ballScore,4)
   // playerFrames:::List[Frame](frame)
 // }

//  def getScore():Int={
//  var total = 0
//  playerFrames.foreach(s=>total=total+s.getCurrentScore)
//    return  total
//  }


}

