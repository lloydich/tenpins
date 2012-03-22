package org.tenpin

import grizzled.slf4j.Logging

object TenPinConsole {
  def main(args: Array[String]):Unit = {
     new TenPinConsole().game()
  }
}
private class TenPinConsole extends Logging{

  def printWinners(game:Game) {
    println("and the winner is ..... "+game.winner())
  }

  def game() {
    info("Starting")

     val players: List[Player] = createPlayers(getNumberOfPlayers)

    var game = new Game(players)
    playGame(game)


    printWinners(game);

//         printTeamScore();
    info("finished")
  }


  def playGame(game: Game) {
    def playFrame(playerId: Int) {
      var playerFrameFinished: Boolean = false

      while (!playerFrameFinished) {
        try {
          println("Please enter Ball score:")
          playerFrameFinished = game.add(playerId, (readLine()).toInt)
        } catch {
          case e: IllegalArgumentException => println("Oops you answered incorrectly! " +e.getMessage)
        }
      }

    }

      for (frame <- 1 to 10) {
        println("Frame:" + frame);
        for (playerNo <- 1 to game.players.size) {
          println("Its Player " + playerNo + "'s (" + game.players(playerNo - 1).name + "): turn...")
          playFrame(playerNo - 1)
          println("Player Score: "+game.score(playerNo - 1))
        }

      }

  }
  def getNumberOfPlayers():Int ={
    println("""|Welcome to TenPinConsole Pin Bowling.
    |How many players are taking part in this game?""".stripMargin)
    try { (readLine()).toInt
      } catch {
          case e: IllegalArgumentException => println("Oops you answered incorrectly! Please enter a number."); getNumberOfPlayers
        }
  }

  def createPlayers(noOfPlayers:Int): List[Player] = {
    var players = List[Player]()
    for (i <- 1 to noOfPlayers) {
      println("Please enter Player " + i + "'s name:")
      val nameOfPlayer = Console.readLine()
      players = players ::: List(new Player(nameOfPlayer))
    }
    players.foreach(player => println(player.name + ", "))
    players
  }


}
