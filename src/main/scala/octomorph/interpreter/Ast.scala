package octomorph.interpreter.Ast

import spire.math.Real

opaque type Program = List[Statement]
object Program:
  def apply(s: List[Statement]): Program = s
  def apply(s: Statement): Program = List(s)
  def apply(): Program = List()

enum Statement:
  case Binding(i: Identifier, t: Type, e: Expression)
  case TypeDefinitionStatement(td: TypeDefinition)
  case ExpressionStatement(e: Expression)

enum Expression:
  case Function(i: Identifier, e: Expression)
  case Application(i: Identifier, e: Expression)
  case Conditional(e1: Expression, e2: Expression, e3: Expression)
  case IdentifierExpression(i: Identifier)
  case LiteralExpression(l: Literal)

enum TypeDefinition:
  case PolymorphicTypeDefinition(
      i: Identifier,
      il: List[Identifier],
      b: TypeDefinitionBody
  )
  case ProperTypeDefinition(i: Identifier, b: TypeDefinitionBody)

enum TypeDefinitionBody:
  case AlgebraicDataType(b: AlgebraicDataTypeBody)
  case RefinementTypeDefinitionBody(t: Type, f: Expression.Function)

enum AlgebraicDataTypeBody:
  case SumType(
      t1: AlgebraicDataTypeBody | Type,
      t2: AlgebraicDataTypeBody | Type
  )
  case ProductType(
      t1: AlgebraicDataTypeBody | Type,
      t2: AlgebraicDataTypeBody | Type
  )

enum Type:
  case FunctionType(t1: Type, t2: Type)
  case Real
  case Unit
  case Void
  case PolymorphicTypeType(pt: PolymorphicType)
  case TypeIdentifier(i: Identifier)

enum PolymorphicType:
  case TypeConstructor(i: Identifier, ts: List[Type])
  case TypeVariable(i: Identifier)

enum Literal:
  case Number(r: Real)
  case Unit

opaque type Identifier = String
object Identifier:
  def apply(s: String): Identifier = s
