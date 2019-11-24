import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

// 2, 3 and 4
trait Shape {

}

case class Circle(r: Float) extends Shape

case class Rectangle(d: Float, h: Float) extends Shape

case class Square(d: Float) extends Shape

def area(shape: Shape): Double = {
  shape match {
    case Circle(r) => Math.PI*r*r
    case Rectangle(d,h) => d*h
    case Square(r) => r*r
  }
}

def circ(shape: Shape): Double = {
  shape match {
    case Circle(r) => Math.PI * r * 2
    case Rectangle(d,h) => 2*d + 2*h
    case Square(s) => 4*s
  }
}


area(Circle(3))

area(Rectangle(2,3))

circ(Circle(3))


//borrowed from
// https://miklos-martin.github.io/learn/fp/category-theory/2018/01/29/adventures-in-category-theory-introduction.html#isomorphism

trait Isomorphism[A, B] {
  def f: A => B
  def g: B => A
}

type <=>[A, B] = Isomorphism[A, B]

def arrowsEqual[A : Arbitrary, B](f: A => B, g: A => B) = forAll { a: A =>
  f(a) == g(a)
}

def arrowIsIdentity[A : Arbitrary](f: A => A) = arrowsEqual[A, A](f, identity)

def proveTypesAreIsomorphic[A : Arbitrary, B](implicit ev: A <=> B) =
  arrowIsIdentity(ev.f andThen ev.g)

// 1. Show the isomorphism between Maybea and Either() a

implicit def evidence[A] = new Isomorphism[Either[Unit, A], Option[A]] {
  def f = {
    case Left(_) => None
    case Right(a) => Option(a)
  }

  def g = {
    case None => Left(())
    case Some(a) => Right(a)
  }
}

proveTypesAreIsomorphic[Either[Unit, String], Option[String]].check


// 5. Show that 𝑎 + 𝑎 = 2 × 𝑎 holds for types (up to isomorphism)

// a + a = Either a a
// 2 * a = (Bool, a)

implicit def evidence2[A] = new Isomorphism[Either[A,A], (Boolean,A)] {
  override def f = {
    case Left(a) => (true, a)
    case Right(a) => (false, a)
  }

  override def g = {
    case (true, a) => Left(a)
    case (false, a) => Right(a)
  }
}

proveTypesAreIsomorphic[Either[String, String], (Boolean, String)].check