package org.tenpin

import grizzled.slf4j.Logging

object Console extends Logging{

  def main(args: Array[String]):Unit = {
    info("Starting")

    var game = new Game(List[Player]())

    game.add(5)
   // game.add(5)
  //  get total score

  }
}
