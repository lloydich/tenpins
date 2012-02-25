package org.tenpin

import org.tenpin.Frame
import grizzled.slf4j.Logging

class Game (playerFrames:List[Frame])  extends Logging {


  def getCurrentFrame():Frame={
    info("playerFrames size:"+playerFrames.size)
    if (playerFrames.last == null) new Frame(0,0)
    else playerFrames.last
  }

  def add(ballScore:Int){
    
    var frame = getCurrentFrame()
    if (frame.isFrameInPlay()) {

    }
    else{
      playerFrames
    }

    
  //  val frame:Frame = new Frame(ballScore,4)
   // playerFrames:::List[Frame](frame)
  }

}

