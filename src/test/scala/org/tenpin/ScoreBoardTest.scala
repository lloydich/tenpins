package org.tenpin

import org.scalatest.FunSuite
import org.scalatest.Assertions._
import collection.immutable.LinearSeq

class ScoreBoardTest extends FunSuite {
  private final val PLAYER_1_NAME: String = "Roy Munson"
  private final val PLAYER_2_NAME: String = "Ernie McCracken"
  private final val PLAYER_3_NAME: String = "Ishmael"

  private final val expectedScoreBoard_player1: String = "\n" +
    "PLAYER Roy Munson\n" +
    "___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________\n" +
    "|    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    |\n" +
    "|  1 |  1 | |  1 |  1 | |  1 |  1 | |  1 |  1 | |  1 |  1 | |  1 |  1 | |  1 |  1 | |  1 |  1 | |  1 |  1 | |  1 |  1 |\n" +
    "|    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____|\n" +
    "|         | |         | |         | |         | |         | |         | |         | |         | |         | |         |\n" +
    "|     2   | |     4   | |     6   | |     8   | |    10   | |    12   | |    14   | |    16   | |    18   | |    20   |\n" +
    "|_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________|\n" +
    "\n";

  private final val expectedScoreBoard_player2: String = "\n" +
    "PLAYER Ernie McCracken\n" +
    "___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________\n" +
    "|    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    |\n" +
    "|  1 |  3 | |  5 |  / | | 10 |  X | |  5 |  4 | |  2 |  1 | |  6 |  / | | 10 |  X | |  2 |  4 | |  5 |  1 | |  8 |  1 |\n" +
    "|    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____|\n" +
    "|         | |         | |         | |         | |         | |         | |         | |         | |         | |         |\n" +
    "|     4   | |    24   | |    43   | |    52   | |    55   | |    75   | |    91   | |    97   | |   103   | |   112   |\n" +
    "|_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________|\n" +
    "\n";

  private final val expectedScoreBoard_player3: String = "\n" +
    "PLAYER Ishmael\n" +
    "___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________ ___________\n" +
    "|    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    | |    |    |\n" +
    "| 10 |  X | | 10 |  X | | 10 |  X | | 10 |  X | | 10 |  X | | 10 |  X | | 10 |  X | | 10 |  X | | 10 |  X | | 10 |  X |\n" +
    "|    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____| |    |____|\n" +
    "|         | |         | |         | |         | |         | |         | |         | |         | |         | |         |\n" +
    "|    30   | |    60   | |    90   | |   120   | |   150   | |   180   | |   210   | |   240   | |   270   | |   300   |\n" +
    "|_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________| |_________|\n" +
    "\n";


  test("score board result should have matched expected where all ballscores same") {
    var player: Player = new Player(PLAYER_1_NAME);
    for (i <- 1 to 20) player.add(1)
    val result: String = ScoreBoard.getScoreBoard(player)
    println(result)
    assert(expectedScoreBoard_player1 == result, true)
  }

  test("score board result should have matched expected where all ballscores different") {
    var player: Player = new Player(PLAYER_2_NAME);
    val scores: LinearSeq[Int] = List(1, 3, 5, 5, 10, 5, 4, 2, 1, 6, 4, 10, 2, 4, 5, 1, 8, 1)
    scores.foreach(score => player.add(score))
    val result: String = ScoreBoard.getScoreBoard(player)
    println(result)
    assert(expectedScoreBoard_player2 == result, true)
  }

  test("score board result should have matched expected where all ballscores strikes") {
    var player: Player = new Player(PLAYER_3_NAME);
    val scores: LinearSeq[Int] = List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)
    scores.foreach(score => player.add(score))
    val result: String = ScoreBoard.getScoreBoard(player)
    println(result)
    assert(expectedScoreBoard_player3 == result, true)
  }
}