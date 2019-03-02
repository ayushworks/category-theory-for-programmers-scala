/**
 * @author Ayush Mittal
 */
class Memoize[-A, +B](f: A => B) {

  private[this] val values = scala.collection.mutable.Map.empty[A, B]

  def apply(v1: A): B =
    if (values.contains(v1)) values(v1)
    else {
      val result = f(v1)
      values += ((v1, result))
      result
    }
}

object Memoize {
  def apply[A, B](f: A => B): Memoize[A, B] = new Memoize(f)
}
