import org.scalatest.Suite
import org.tenpin.Frame

class FrameSuite extends Suite {
  def testFrameScoreGetScore() {
    val frameScore= new Frame(4, 5)
    assert(frameScore.getScore() === 9)
  }

  def testFrameScoreGetNoScore() {
    val frameScore= new Frame(0, 0)
    assert(frameScore.getScore() === 0)
    expect(0) {
      frameScore.getScore()
    }
  }
  def testFrameScoreIsASpare() {
    val frameScore = new Frame(5, 5)
    assert(frameScore.isASpare()==true)
  }

  def testFrameScoreIsAStrike() {
    val frameScore = new Frame(10, 0)
    assert(frameScore.isAStrike==true)
  }
}