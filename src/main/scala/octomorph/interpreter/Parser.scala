package octomorph.interpreter.Parser

import mouse.all._
import scala.util.matching.Regex
import octomorph.interpreter.Ast._

opaque type Tokens = List[String]
object Tokens:
  def apply(ss: List[String]): Tokens = ss
  def apply(s: String): Tokens = List(s)
  def apply(): Tokens = List()

def parse(s: String): ParseResult = ???

def parseProgram(tokens: Tokens): ParseResult = ???

def skipWhitespace(tokens: Tokens): Tokens =
  tokens match
    case Nil => tokens
    case t :: ts =>
      if t == " " || t == "\n" || t == "\r" || t == "\t" then skipWhitespace(ts)
      else tokens
