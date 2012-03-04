package org.tenpin

class Player {
               //to be moved to player object inside list
 var frameScores:List[Int]   = List[Int]()
var ballScores:List[List[Int]]   = List[List[Int]]()

  
  def score():Int={
    if(frameScores.isEmpty) 0 else frameScores.reduceLeft((a:Int,  b:Int) => a + b )
  }
  
  def add(ballScore:Int){
    println("\nadding "+ballScore)
               //get size of ballscores
    //if not 0 get last and initialise frame see if its done
    //if not done add to frame otherwise create new array and add
    //if frame finished add to frameScore
    def getCurrentFrame:List[Int] = {
      println("getCurrentFrame  ")
      if (ballScores.isEmpty) {
        println("getCurrentFrame  if")
        ballScores=ballScores:::List(List(ballScore))
        List(ballScore)
      }
      else {

        var currentFrame = ballScores.last
        println("getCurrentFrame else currentFrame:"+currentFrame)
        if (!Frame.isFrameInPlay(currentFrame)){
          println("getCurrentFrame else if - frame is NOT in play")
          ballScores=ballScores:::List(List(ballScore))
            List(ballScore)
        }
        else {
          println("getCurrentFrame else else - frame IS in play")
           currentFrame = List(ballScore):::currentFrame
          println("currentFrame updated to:"+currentFrame)
          ballScores=ballScores.dropRight(1)
          ballScores = ballScores:::List(currentFrame)
          currentFrame
        }
      }
    }
    var currentFrame:List[Int]  = getCurrentFrame
    if (!Frame.isFrameInPlay(currentFrame))
      frameScores = frameScores:::List(Frame.getCurrentScore(currentFrame))
  }
}
