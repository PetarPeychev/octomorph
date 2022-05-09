package octomorph.interpreter.Parser

import mouse.all._
import scala.util.matching.Regex
import octomorph.interpreter.Ast._

object Parser:
  opaque type Tokens = List[String]
  object Tokens:
    def apply(ss: List[String]): Tokens = ss
    def apply(s: String): Tokens = List(s)
    def apply(): Tokens = List()

  def parse(s: String): Either[Program, String] =
    val tokens: List[String] = s.split("").toList
    parseProgram(tokens)

  def parseProgram(tokens: Tokens): Either[Program, String] =
    skipWhitespace(tokens) match
      case Nil => Left(Program())
      // case ts  => ts |> parseStatement |> skipToken(";") |> parseProgram

  def parseStatement(tokens: List[String]): Either[Statement, String] =
    ???

  def skipToken(token: String)(tokens: Tokens): Tokens =
    ???

  def skipWhitespace(tokens: Tokens): Tokens =
    tokens match
      case Nil => tokens
      case t :: ts =>
        if t == " " || t == "\n" || t == "\r" || t == "\t" then
          skipWhitespace(ts)
        else tokens
