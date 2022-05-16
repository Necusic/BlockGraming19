package com.example.blockgraming19

import android.widget.TextView
import java.lang.RuntimeException

internal class Parser(private val tokens: List<Token>, private val currentTextView : TextView) {
    private val textView : TextView
    private val size: Int
    private var pos = 0
    fun parse(): Statement {
        val result = BlockStatement()
        while (!match(TokenType.EOF)) {
            result.add(statement())
        }
        return result
    }

    private fun block(): Statement {
        val block = BlockStatement()
        consume(TokenType.LBRACE)
        while (!match(TokenType.RBRACE)) {
            block.add(statement())
        }
        return block
    }

    private fun statementOrBlock(): Statement {
        return if (get(0).type === TokenType.LBRACE) block() else statement()
    }

    private fun statement(): Statement {
        if (match(TokenType.PRINT)) {
            return PrintStatement(expression(), textView)
        }
        if (match(TokenType.IF)) {
            return ifElse()
        }
        if (match(TokenType.WHILE)) {
            return whileStatement()
        }
        if (match(TokenType.BREAK)) {
            return BreakStatement()
        }
        if (match(TokenType.CONTINUE)) {
            return ContinueStatement()
        }
        if (match(TokenType.FOR)) {
            return forStatement()
        }
        return if (get(0).type === TokenType.WORD && get(1).type === TokenType.LPAREN) {
            FunctionStatement(function())
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
        val ifStatement = statementOrBlock()
        val elseStatement: Statement?
        elseStatement = if (match(TokenType.ELSE)) {
            statementOrBlock()
        } else {
            null
        }
        return IfStatement(condition, ifStatement, elseStatement)
    }

    private fun whileStatement(): Statement {
        val condition = expression()
        val statement = statementOrBlock()
        return WhileStatement(condition, statement)
    }
    private fun forStatement(): Statement {
        val initialization = assignmentStatement()
        consume(TokenType.COMMA)
        val termination = expression()
        consume(TokenType.COMMA)
        val increment = assignmentStatement()
        val statement = statementOrBlock()
        return ForStatement(initialization, termination, increment, statement)
    }

    private fun function(): FunctionalExpression {
        val name = consume(TokenType.WORD).text
        consume(TokenType.LPAREN)
        val function = FunctionalExpression(name!!)
        while (!match(TokenType.RPAREN)) {
            function.addArgument(expression())
            match(TokenType.COMMA)
        }
        return function
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
            return ValueExpression(current.text!!.toDouble())
        }
        if (get(0).type === TokenType.WORD && get(1).type === TokenType.LPAREN) {
            return function()
        }
        if (match(TokenType.WORD)) {
            return VariableExpression(current.text!!)
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
        textView = currentTextView
    }
}