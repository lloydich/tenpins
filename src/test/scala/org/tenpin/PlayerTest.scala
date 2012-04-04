package org.tenpin

import org.scalatest.FunSuite

class PlayerTest extends FunSuite {
  test("score result should have matched expected") {
    val player = new Player("test name")

    //frame1
    player.add(1)
    printStuff(player)
    assert(player.score == 0)

    player.add(2)
    printStuff(player)
    assert(player.score == 3)

     //frame2
    player.add(5)
    printStuff(player)
    assert(player.score == 3)

    player.add(4)
    printStuff(player)
    assert(player.score == 12)

     //frame3
    player.add(10)
    printStuff(player)
    assert(player.score == 12)

     //frame4
    player.add(5)
    printStuff(player)
    assert(player.score == 12)

    player.add(4)
    printStuff(player)
    assert(player.score == 40)

     //frame5
    player.add(4)
    printStuff(player)
    assert(player.score == 40)

    player.add(6)
    printStuff(player)
    assert(player.score == 40)

     //frame6
    player.add(2)
    printStuff(player)
    assert(player.score == 40)

    player.add(2)
    printStuff(player)
    assert(player.score == 56)

     //frame7
     player.add(10)
    printStuff(player)
    assert(player.score == 56)

     //frame8
    player.add(10)
    printStuff(player)
    assert(player.score == 56)

     //frame9
     player.add(5)
    printStuff(player)
    assert(player.score == 56)

     player.add(3)
    printStuff(player)
    assert(player.score == 107)

     //frame10
     player.add(4)
    printStuff(player)
    assert(player.score == 107)

     player.add(2)
    printStuff(player)
    assert(player.score == 113)
  }

   test("score result should have matched expected where all strikes") {
    val player = new Player("test name")

    player.add(10)
    assert(player.score == 0)

    player.add(10)
    assert(player.score == 0)

    player.add(10)
    assert(player.score == 30)

    player.add(10)
    assert(player.score == 60)


    player.add(10)
    assert(player.score == 90)

    player.add(10)
    assert(player.score == 120)

    player.add(10)
    assert(player.score == 150)


    player.add(10)
    assert(player.score == 180)

    player.add(10)
    assert(player.score == 210)


    player.add(10)
    assert(player.score == 210)


    player.add(10)
    assert(player.score == 210)


    player.add(10)
    assert(player.score == 300)

   }

   test("score result should have matched expected where testing spare in TenthFrame") {
    val player = new Player("test name")
     for (i <- 1 to 21)  player.add(5) ;
       assert(player.score == 150)
   }

   def printStuff(player:Player) {
      println(player.score())
      println(player.ballScores)
      println(player.frameScores)
    }

}
