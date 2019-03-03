import scala.language.higherKinds

/**
 * @author Ayush Mittal
 */
object Implementations {

  val safe_root: Double => Optional[Double] = x => {
    if (x >= 0) new Some[Double](Math.sqrt(x))
    else None
  }

  val safe_reciprocal: Double => Optional[Double] = x => {
    if (x != 0) new Some[Double](1 / x)
    else None
  }

  def safe_root_reciprocal(x: Double): Optional[Double] = {
    import kleisli._
    safe_reciprocal.>=>(safe_root)(x)
  }
}
