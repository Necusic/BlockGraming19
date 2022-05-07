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
        return conditional()
    }
    private  fun conditional():Expression{
        var result = additive()
        while (true) {
            if (match(TokenType.EQ)) {
                result = ConditionalExpression('=', result, additive())
                continue
            }
            if (match(TokenType.LT)) {
                result = ConditionalExpression('<', result, additive())
                continue
            }
            if (match(TokenType.GT)) {
                result = ConditionalExpression('>', result, additive())
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
        //Тут может быть проблема
        if (match(TokenType.HEX_NUMBER)) {
            return ValueExpression(current.text!!.toLong(16).toDouble())
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
        val EOF = Token(TokenType.EOF, "")
    }

    init {
        size = tokens.size
    }
}