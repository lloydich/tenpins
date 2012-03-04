package org.tenpin

import org.scalatest.FunSuite

class PlayerTest extends FunSuite {
  test("score result should have matched expected") {
    val player = new Player()
    def printStuff {
      println(player.score())
      println(player.ballScores)                                                                                                                                                                                                                                                         package org.tenpin

      import org.scalatest.FunSuite

      class PlayerTest extends FunSuite {
        test("score result should have matched expected") {
          val player = new Player()
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

      //    player.add(4)
      //    printStuff
      //    assert(player.score == 12)
        }
      }

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
  }
}
