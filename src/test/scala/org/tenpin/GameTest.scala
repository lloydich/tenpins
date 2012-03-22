package org.tenpin

import org.scalatest.FunSuite

class GameTest extends FunSuite {
  def printStuff(players: List[Player]) {
    for (player <- players) {
      println(player.score())
      println(player.ballScores)
      println(player.frameScores)
    }
  }

  test("Game winner should have matched expected") {
    val playerOne = new Player("Player 1")
    val playerTwo = new Player("Player 2")
    val playerThree = new Player("Player 3")

    var game: Game = new Game(List(playerOne, playerTwo, playerThree));
    game.add(0, 5)
    game.add(0, 1)

    game.add(1, 7)
    game.add(1, 2)

    game.add(2, 2)
    game.add(2, 1)

    printStuff(List(playerOne, playerTwo, playerThree))

    assert(game.winner == List(playerTwo))
  }

  test("Game winners should have matched expected") {
    val playerOne = new Player("Player 1")
    val playerTwo = new Player("Player 2")
    val playerThree = new Player("Player 3")

    var game: Game = new Game(List(playerOne, playerTwo, playerThree));
    game.add(0, 5)
    game.add(0, 4)

    game.add(1, 7)
    game.add(1, 2)

    game.add(2, 2)
    game.add(2, 1)

    printStuff(List(playerOne, playerTwo, playerThree))

    assert(game.winner == List(playerOne, playerTwo))
  }


}
