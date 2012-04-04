import org.scalatest.Suite
import org.tenpin.FrameScore
import org.tenpin.Score.Frame

class FrameSuite extends Suite {
  def testFrameScoreGetScore() {
    assert(FrameScore.getCurrentScore(List(4, 5)) === 9)
  }

  def testFrameScoreGetNoScore() {
    assert(FrameScore.getCurrentScore(List()) === 0)
    //    expect(0) {
    //      Frame.getCurrentScore(List(0))
    //    }
  }

  def testFrameScoreIsASpare() {
    assert(FrameScore.isASpare(Frame(1, List(5, 5))) == true)
  }

  def testFrameScoreIsNotASpare() {
    assert(FrameScore.isASpare(Frame(1, List(4, 5))) == false)
  }

  def testFrameScoreIsASpareForTenthFrame() {
    assert(FrameScore.isASpare(Frame(10, List(6, 4))) == true)
  }

  def testFrameScoreIsNotASpareForTenthFrame() {
    assert(FrameScore.isASpare(Frame(10, List(10, 6, 4))) == false)
  }

  def testFrameScoreIsAStrike() {
    assert(FrameScore.isAStrike(List(10, 0)) == true)
  }

  def testFrameScoreIsAlsoAStrike() {
    assert(FrameScore.isAStrike(List(10)) == true)
  }

  def testFrameScoreIsNotAStrike() {
    assert(FrameScore.isAStrike(List(3, 7)) == false)
  }
}