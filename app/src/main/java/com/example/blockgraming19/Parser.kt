package com.example.blockgraming19

class Parser(private val tokens: List<Token>) {
    private val size: Int
    private var pos = 0
    fun parse(): List<Statement> {
        val result: MutableList<Statement> = ArrayList()
        while (!match(TokenType.EOF)) {
            result.add(statement())
        }
        return result
    }

    private fun statement(): Statement {
        if (match(TokenType.PRINT)) {
            return PrintStatement(expression())
        }
        return if (match(TokenType.IF)) {
            ifElse()
        } else assignmentStatement()
    }

    private fun assignmentStatement(): Statement {
        // WORD EQ
        val current = get(0)
        if (match(TokenType.WORD) && get(0).type === TokenType.EQ) {
            val variable = current.text
            consume(TokenType.EQ)
            return AssignmentStatement(variable!!, expression())
        }
        throw RuntimeException("Unknown statement")
    }

    private fun ifElse(): Statement {
        val condition = expression()
        val ifStatement = statement()
        val elseStatement: Statement?
        elseStatement = if (match(TokenType.ELSE)) {
            statement()
        } else {
            null
        }
        return IfStatement(condition, ifStatement, elseStatement)
    }

    private fun expression(): Expression {
        return logicalOr()
    }

    private fun logicalOr(): Expression {
        var result = logicalAnd()
        while (true) {
            if (match(TokenType.BARBAR)) {
                result =
                    ConditionalExpression(ConditionalExpression.Operator.OR, result, logicalAnd())
                continue
            }
            break
        }
        return result
    }

    private fun logicalAnd(): Expression {
        var result = equality()
        while (true) {
            if (match(TokenType.AMPAMP)) {
                result =
                    ConditionalExpression(ConditionalExpression.Operator.AND, result, equality())
                continue
            }
            break
        }
        return result
    }

    private fun equality(): Expression {
        val result = conditional()
        if (match(TokenType.EQEQ)) {
            return ConditionalExpression(
                ConditionalExpression.Operator.EQUALS,
                result,
                conditional()
            )
        }
        return if (match(TokenType.EXCLEQ)) {
            ConditionalExpression(
                ConditionalExpression.Operator.NOT_EQUALS,
                result,
                conditional()
            )
        } else result
    }

    private fun conditional(): Expression {
        var result = additive()
        while (true) {
            if (match(TokenType.LT)) {
                result =
                    ConditionalExpression(ConditionalExpression.Operator.LT, result, additive())
                continue
            }
            if (match(TokenType.LTEQ)) {
                result =
                    ConditionalExpression(ConditionalExpression.Operator.LTEQ, result, additive())
                continue
            }
            if (match(TokenType.GT)) {
                result =
                    ConditionalExpression(ConditionalExpression.Operator.GT, result, additive())
                continue
            }
            if (match(TokenType.GTEQ)) {
                result =
                    ConditionalExpression(ConditionalExpression.Operator.GTEQ, result, additive())
                continue
            }
            break
        }
        return result
    }

    private fun additive(): Expression {
        var result = multiplicative()
        while (true) {
            if (match(TokenType.PLUS)) {
                result = BinaryExpression('+', result, multiplicative())
                continue
            }
            if (match(TokenType.MINUS)) {
                result = BinaryExpression('-', result, multiplicative())
                continue
            }
            break
        }
        return result
    }

    private fun multiplicative(): Expression {
        var result = unary()
        while (true) {
            // 2 * 6 / 3
            if (match(TokenType.STAR)) {
                result = BinaryExpression('*', result, unary())
                continue
            }
            if (match(TokenType.SLASH)) {
                result = BinaryExpression('/', result, unary())
                continue
            }
            break
        }
        return result
    }

    private fun unary(): Expression {
        if (match(TokenType.MINUS)) {
            return UnaryExpression('-', primary())
        }
        return if (match(TokenType.PLUS)) {
            primary()
        } else primary()
    }

    private fun primary(): Expression {
        val current = get(0)
        if (match(TokenType.NUMBER)) {
            return ValueExpression(current.text!!.toDouble())
        }
        if (match(TokenType.HEX_NUMBER)) {
            //Тут может быть проблема !
            return ValueExpression(current.text!!.toDouble())
        }
        if (match(TokenType.WORD)) {
            return VariabletExpression(current.text!!)
        }
        if (match(TokenType.TEXT)) {
            return ValueExpression(current.text)
        }
        if (match(TokenType.LPAREN)) {
            val result = expression()
            match(TokenType.RPAREN)
            return result
        }
        throw RuntimeException("Unknown expression")
    }

    private fun consume(type: TokenType): Token {
        val current = get(0)
        if (type !== current.type) throw RuntimeException("Token $current doesn't match $type")
        pos++
        return current
    }

    private fun match(type: TokenType): Boolean {
        val current = get(0)
        if (type !== current.type) return false
        pos++
        return true
    }

    private operator fun get(relativePosition: Int): Token {
        val position = pos + relativePosition
        return if (position >= size) EOF else tokens[position]
    }

    companion object {
        private val EOF = Token(TokenType.EOF, "")
    }

    init {
        size = tokens.size
    }
}