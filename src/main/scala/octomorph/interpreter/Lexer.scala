package octomorph.interpreter.Lexer

import scala.util.matching.Regex
import spire.math.Real
import octomorph.interpreter.Token._
import octomorph.interpreter.Token.Token._

def tokenize(s: String): List[Token] =
  def tokenizeRec(ts: List[Token], cs: List[Char]): List[Token] =
    parseToken(cs) match
      case (EOF, cs) => (EOF :: ts).reverse
      case (t, cs)   => tokenizeRec(t :: ts, cs)

  tokenizeRec(List(), s.toCharArray.toList)

def parseToken(cs: List[Char]): (Token, List[Char]) =
  cs match
    case Nil                                      => (EOF, List())
    case c :: cs if raw"\s".r.matches(c.toString) => parseToken(cs)
    case c :: cs if raw"\d".r.matches(c.toString) => parseNumber(c.toString, cs)
    case ';' :: cs                                => (SEMICOLON, cs)
    case ':' :: cs                                => (COLON, cs)
    case '(' :: cs                                => (LPAREN, cs)
    case ')' :: cs                                => (RPAREN, cs)
    case '\\' :: cs                               => (BSLASH, cs)
    case '=' :: cs                                => (EQUALS, cs)
    case c :: cs                                  => ???

def parseNumber(num: String, cs: List[Char]): (Token, List[Char]) =
  val number = raw"(\"0\" | [1-9] [0-9]*)(\".\" [0-9]+ )?".r
  cs match
    case c :: cs if number.matches(num + c) => parseNumber(num + c, cs)
    case cs                                 => (NUMBER(Real(num)), cs)
