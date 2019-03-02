/**
 * @author Ayush Mittal
 */
class Memoize[-A, +B](f: A => B) extends (A => B) {

  private[this] val values = scala.collection.mutable.Map.empty[A, B]

  def apply(v1: A): B =
    values.getOrElseUpdate(v1, f(v1))
}

object Memoize {
  def apply[A, B](f: A => B): Memoize[A, B] = new Memoize(f)
}
