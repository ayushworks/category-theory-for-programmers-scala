import org.scalatest.{ FlatSpec, Matchers, WordSpec }

import scala.util.Random

/**
 * @author Ayush Mittal
 */
class MemoizeTest extends WordSpec with Matchers {

  "My Memoize class" should {

    "produce same result as original function" in {

      /* a function that we would like to memoize*/
      def f(a: String): Int = a.length

      val input         = "test-input"
      val result        = f(input)
      val memoizeResult = Memoize(f)(input)
      memoizeResult shouldBe result
    }

    "calls original functions once for same arguments " in {

      var counter = 0

      def f(a: String): Int = {
        counter += 1
        a.length
      }

      val input     = "test-input"
      val fMemoized = Memoize(f)

      fMemoized(input)
      fMemoized(input)
      counter shouldBe 1
    }

    "works with random value generator using an input seed" in {

      def f(a: Int): Double = {
        val random = new Random(a)
        random.nextDouble()
      }

      val fMemoized = Memoize(f)

      val result1 = fMemoized(32)
      val result2 = fMemoized(32)

      result1 shouldBe result2
    }
  }

}
