package com.example.blockgraming19

class Lexer(private val input: String) {
    private val length: Int
    private val tokens: MutableList<Token>
    private var pos = 0
    fun tokenize(): List<Token> {
        while (pos < length) {
            val current = peek(0)
            if (Character.isDigit(current)) tokenizeNumber() else if (Character.isLetter(current)) tokenizeWord() else if (current == '"') tokenizeText() else if (current == '#') {
                next()
                tokenizeHexNumber()
            } else if (OPERATOR_CHARS.indexOf(current) != -1) {
                tokenizeOperator()
            } else {
                // Белые места
                next()
            }
        }
        return tokens
    }

    private fun tokenizeNumber() {
        val buffer = StringBuilder()
        var current = peek(0)
        while (true) {
            if (current == '.') {
                if (buffer.indexOf(".") != -1) throw RuntimeException("Invalid float number")
            } else if (!Character.isDigit(current)) {
                break
            }
            buffer.append(current)
            current = next()
        }
        addToken(TokenType.NUMBER, buffer.toString())
    }

    private fun tokenizeHexNumber() {
        val buffer = StringBuilder()
        var current = peek(0)
        while (Character.isDigit(current) || isHexNumber(current)) {
            buffer.append(current)
            current = next()
        }
        addToken(TokenType.HEX_NUMBER, buffer.toString())
    }

    private fun tokenizeOperator() {
        val position = OPERATOR_CHARS.indexOf(peek(0))
        addToken(OPERATOR_TOKENS[position])
        next()
    }

    private fun tokenizeWord() {
        val buffer = StringBuilder()
        var current = peek(0)
        while (true) {
            if (!Character.isLetterOrDigit(current) && current != '_' && current != '$') {
                break
            }
            buffer.append(current)
            current = next()
        }
        val word = buffer.toString()
        if (word == "print") {
            addToken(TokenType.PRINT)
        } else {
            addToken(TokenType.WORD, word)
        }
    }

    private fun tokenizeText() {
        next() // skip "
        val buffer = StringBuilder()
        var current = peek(0)
        while (true) {
            if (current == '\\') {
                current = next()
                when (current) {
                    '"' -> {
                        current = next()
                        buffer.append('"')
                        continue
                    }
                    'n' -> {
                        current = next()
                        buffer.append('\n')
                        continue
                    }
                    't' -> {
                        current = next()
                        buffer.append('\t')
                        continue
                    }
                }
                buffer.append('\\')
                continue
            }
            if (current == '"') break
            buffer.append(current)
            current = next()
        }
        next() // skip closing "
        addToken(TokenType.TEXT, buffer.toString())
    }

    private operator fun next(): Char {
        pos++
        return peek(0)
    }

    private fun peek(relativePosition: Int): Char {
        val position = pos + relativePosition
        return if (position >= length) '\u0000' else input[position]
    }

    private fun addToken(type: TokenType, text: String = "") {
        tokens.add(Token(type, text))
    }

    companion object {
        private const val OPERATOR_CHARS = "+-*/()="
        private val OPERATOR_TOKENS = arrayOf(
            TokenType.PLUS, TokenType.MINUS,
            TokenType.STAR, TokenType.SLASH,
            TokenType.LPAREN, TokenType.RPAREN,
            TokenType.EQ
        )

        private fun isHexNumber(current: Char): Boolean {
            return "abcdef".indexOf(Character.toLowerCase(current)) != -1
        }
    }

    init {
        length = input.length
        tokens = ArrayList()
    }
}