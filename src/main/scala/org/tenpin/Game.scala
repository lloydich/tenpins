package org.tenpin

import org.tenpin.Frame
import grizzled.slf4j.Logging

class Game(players:List[Player])   extends Logging {




//or frame obj with list of sballscores and scores - no can't set score to null

//  def getCurrentFrame():Frame={
//    info("playerFrames size:"+playerFrames.size)
//    if (playerFrames.last == null) new Frame(0,0)
//    else playerFrames.last
//  }

//    def getCurrentFrame():Array={
//      info("playerFrames size:"+playerFrames.length)
//      return playerFrames.last
//
//    }

  def add(ballScore:Int){

    //get size of ballscores
    //if not 0 get ladt and initialise frame see if its done
    //if not done add to frame otherwise create new array and add
    //if frame finished add to frameScore
    

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

