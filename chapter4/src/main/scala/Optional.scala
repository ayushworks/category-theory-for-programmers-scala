/**
 * @author Ayush Mittal
 */
/**
 * An optional type as described in chapter -4.
 */
trait Optional[+A] {
  def isValid: Boolean
  def value: A
}

case class Some[A](a: A) extends Optional[A] {
  override def isValid: Boolean = true

  override def value: A = a
}

case object None extends Optional[Nothing] {
  override def isValid: Boolean = false

  override def value: Nothing = throw new RuntimeException("value called on None")
}

/*
  A Kleisli category for partial functions
 */
object kleisli {
  implicit class KleisliOps[A, B](m1: A => Optional[B]) {
    def >=>[C](m2: B => Optional[C]): A => Optional[C] =
      x => {
        val result1 = m1(x)
        if (result1.isValid) {
          m2(result1.value)
        } else {
          None
        }
      }

    def pure[A](x: A): Optional[A] = new Some[A](x)
  }
}
