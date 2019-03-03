/**
 * @author Ayush Mittal
 */
class Bool2Bool {

  /*
  In terms of category theory i assume there are only 4 arrows possible
  true -> false
  false -> true
  true -> true
  false -> false
  f1 and f2 represent these 4 arrows
   */
  def f1(bool: Boolean) = !bool

  def f2(bool: Boolean) = bool
}
