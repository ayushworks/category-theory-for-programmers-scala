import org.scalatest.{ Matchers, WordSpec }

/**
 * @author Ayush Mittal
 */
class ImplementationsSpec extends WordSpec with Matchers {

  import Implementations._

  "Safe square root" should {

    "calculate square root of 16" in {
      val result = safe_root(16)
      result.isValid shouldBe true
      result.value shouldBe 4
    }

    "return invalid for -4" in {
      val result = safe_root(-4)
      result.isValid shouldBe false
    }
  }

  "Safe reciprocal" should {

    "calculate reciprocal of 2" in {
      val result = safe_reciprocal(2)
      result.isValid shouldBe true
      result.value shouldBe 0.5
    }

    "return invalid for 0" in {
      val result = safe_reciprocal(0)
      result.isValid shouldBe false
    }
  }

  "Safe root and reciprocal compose" should {

    "calculate reciprocal and then root of 0.25" in {
      val result = safe_root_reciprocal(0.25)
      result.isValid shouldBe true
      result.value shouldBe 2
    }

    "return invalid for 0" in {
      val result = safe_root_reciprocal(0)
      result.isValid shouldBe false
    }

    "return invalid for -0.25" in {
      val result = safe_root_reciprocal(-0.25)
      result.isValid shouldBe false
    }
  }
}
