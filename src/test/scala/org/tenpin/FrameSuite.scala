import org.scalatest.Suite
import org.tenpin.Frame

class FrameSuite extends Suite {
  def testFrameScoreGetScore() {
    assert(Frame.getCurrentScore(List(4, 5)) === 9)
  }

  def testFrameScoreGetNoScore() {
    assert(Frame.getCurrentScore(List()) === 0)
//    expect(0) {
//      Frame.getCurrentScore(List(0))
//    }
  }
  def testFrameScoreIsASpare() {
    assert(Frame.isASpare(List(5,5))==true)
  }

  def testFrameScoreIsAStrike() {
    assert(Frame.isAStrike(List(10,0))==true)
  }
}