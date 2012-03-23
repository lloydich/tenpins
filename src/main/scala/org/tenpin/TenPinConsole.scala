package org.tenpin

import grizzled.slf4j.Logging

object TenPinConsole {
  def main(args: Array[String]):Unit = {
     new TenPinConsole().game()
  }
}
private class TenPinConsole extends Logging{

  def printWinners(game:Game) {
    print("and the winner is..... ")
    for(player <- game.winner()) println(player.name+" ")
  }




  def game() {
    info("Starting")

    val teams:List[List[Player]] = createTeams(getNumberOfTeams)


    var game = new Game(teams)
    println("There are "+game.teams.size+" teams")
    playGame(game)


    printWinners(game);

//         printTeamScore();
    info("finished")
  }


  def playGame(game: Game) {
    def playFrame(teamId:Int,  playerId: Int) {
      var playerFrameFinished: Boolean = false

      while (!playerFrameFinished) {
        try {
          println("Please enter Ball score:")
          playerFrameFinished = game.add(teamId, playerId, (readLine()).toInt)
        } catch {
          case e: IllegalArgumentException => println("Oops you answered incorrectly! " +e.getMessage)
        }
      }

    }

      for (frame <- 1 to 10) {
        println("Frame:" + frame);
         for (teamNo <- 1 to game.teams.size) {
            println("Team:" + teamNo);
            for (playerNo <- 1 to game.teams(teamNo-1).size) {
              println("Its Player " + playerNo + "'s (" + game.teams(teamNo-1)(playerNo - 1).name + "): turn...")
              playFrame(teamNo-1, playerNo - 1)
              println("Player Score: "+game.score(teamNo-1,playerNo - 1))
            }
         }
      }

  }
  def getNumberOfPlayers(teamNumber:Int):Int ={
    println("How many players are in team "+teamNumber+"?")
    try { (readLine()).toInt
      } catch {
          case e: IllegalArgumentException => println("Oops you answered incorrectly! Please enter a number."); getNumberOfPlayers(teamNumber)
        }
  }

   def getNumberOfTeams :Int ={
    println("""|Welcome to TenPinConsole Pin Bowling.
    |How many teams are taking part in this game?""".stripMargin)
    try { (readLine()).toInt
      } catch {
          case e: IllegalArgumentException => println("Oops you answered incorrectly! Please enter a number."); getNumberOfTeams
        }
  }

  def createPlayers(noOfPlayers:Int): List[Player] = {
    var players = List[Player]()
    for (i <- 1 to noOfPlayers) {
      println("Please enter Player " + i + "'s name:")
      val nameOfPlayer = Console.readLine()
      players = players ::: List(new Player(nameOfPlayer))
    }
    //players.foreach(player => println(player.name + ", "))
    players
  }

  def createTeams(noOfTeams: Int):List[List[Player]] ={
   var teams:List[List[Player]] = List()
     for (i <- 1 to noOfTeams) {
         teams = teams ::: List(createPlayers(getNumberOfPlayers(i)))
     }
    teams
  }


}
