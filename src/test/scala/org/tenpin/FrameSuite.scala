import org.scalatest.Suite
import org.tenpin.FrameScore
import org.tenpin.Frame

class FrameSuite extends Suite with FrameScore {
  def testFrameScoreGetScore() {
    assert(currentScore(List(4, 5)) === 9)
  }

  def testFrameScoreGetNoScore() {
    assert(currentScore(List()) === 0)
    //    expect(0) {
    //      Frame.getCurrentScore(List(0))
    //    }
  }

  def testFrameScoreIsASpare() {
    assert(isASpare(Frame(1, List(5, 5))) == true)
  }

  def testFrameScoreIsNotASpare() {
    assert(isASpare(Frame(1, List(4, 5))) == false)
  }

  def testFrameScoreIsASpareForTenthFrame() {
    assert(isASpare(Frame(10, List(6, 4))) == true)
  }

  def testFrameScoreIsNotASpareForTenthFrame() {
    assert(isASpare(Frame(10, List(10, 6, 4))) == false)
  }

  def testFrameScoreIsAStrike() {
    assert(isAStrike(List(10, 0)) == true)
  }

  def testFrameScoreIsAlsoAStrike() {
    assert(isAStrike(List(10)) == true)
  }

  def testFrameScoreIsNotAStrike() {
    assert(isAStrike(List(3, 7)) == false)
  }
}