/**
  * @author Ayush Mittal
  */

/*
4. Implement the equivalent of Haskell Either as a generic type in
your favorite language (other than Haskell).
 */
trait MyEither[A,B]


final case class MyLeft[A,B](value: A) extends MyEither[A,B]

final case class MyRight[A,B](value: B) extends MyEither[A, B]



def p : Int => MyEither[Int, Boolean] = int => MyLeft(int)

def q: Boolean => MyEither[Int, Boolean] = bool => MyRight(bool)

/*
5. Show that Either is a “better” coproduct than int equipped with
two injections:
   int i(int n) { return n; }
   int j(bool b) { return b ? 0: 1; }
 */

def p1 : Int => Int = _
def q1 : Boolean => Int = bool => if(bool) 0 else 1

/*
  we need to define a morphism m that goes from c(Either) to c`(Int) and factorises p1 and p2
 */
def m : MyEither[Int, Boolean] => Int = either =>
  either match {
    case MyLeft(int) => int
    case MyRight(boolean) => if(boolean) 1 else 0

  }

/*
  Now p1 and p2 can be written by using m and p/q
 */

def p1New : Int => Int = {
  int =>
    m(p(int)) // m compose p
}

def q1New : Boolean => Int = {
  bool =>
    m(q(bool)) //m compose q
}

/*
6. Continuing the previous problem: How would you argue that int
with the two injections i and j cannot be “better” than Either?

Can we define a function that goes from Int to Either?
 */

def notPossible : Int => MyEither[Int, Boolean] = ???

/*
7. Still continuing: What about these injections?
   int i(int n) {
       if (n < 0) return n;
       return n + 2;
   }
   int j(bool b) { return b ? 0: 1; }
 */

def p2 : Int => Int = int => {
  if(int >0) int
  else int+2
}

def q2: Boolean => Int = bool => if(bool) 0 else 1

/*
  Same logic again. p2 and q2 can be factorized using m
 */

/*
  8. Come up with an inferior candidate for a coproduct of int and bool that cannot be better than Either
  because it allows multiple acceptable morphisms from it to Either.
 */

/*
Lets choose a type c` as Boolean
 */

def p3 : Int => Boolean = _ => true

def q3: Boolean => Boolean = _

def m1 : Boolean => MyEither[Int, Boolean] = {
  bool => MyRight(bool)
}

def m2: Boolean => MyEither[Int, Boolean] = {
  bool => MyLeft(1)
}

def m3: Boolean => MyEither[Int, Boolean] = {
  bool => MyLeft(0)
}
