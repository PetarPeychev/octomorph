package octomorph.interpreter.Token

import spire.math.Real

enum Token:
  case SEMICOLON
  case COLON
  case LPAREN
  case RPAREN
  case BSLASH
  case EQUALS
  case ARROW
  case LET
  case REAL
  case UNIT
  case VOID
  case NUMBER(real: Real)
  case IDENTIFIER(id: String)
  case EOF
