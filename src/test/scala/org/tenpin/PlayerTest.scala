package org.tenpin

import org.scalatest.FunSuite

class PlayerTest extends FunSuite {
  test("score result should have matched expected") {
    val player = new Player("test name")
    def printStuff {
      println(player.score())
      println(player.ballScores)
      println(player.frameScores)
    }
    player.add(1)
    printStuff
    assert(player.score == 0)

    player.add(2)
    printStuff
    assert(player.score == 3)

    player.add(5)
    printStuff
    assert(player.score == 3)

    player.add(4)
    printStuff
    assert(player.score == 12)

    player.add(10)
    printStuff
    assert(player.score == 12)

    player.add(5)
    printStuff
    assert(player.score == 12)

    player.add(4)
    printStuff
    assert(player.score == 40)

    player.add(4)
    printStuff
    assert(player.score == 40)

    player.add(6)
    printStuff
    assert(player.score == 40)

    player.add(2)
    printStuff
    assert(player.score == 40)

    player.add(2)
    printStuff
    assert(player.score == 56)
  }


}
