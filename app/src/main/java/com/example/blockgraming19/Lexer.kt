package com.example.blockgraming19

class Lexer(private val input: String) {
    companion object {
        private const val OPERATOR_CHARS = "+-*/(){}=<>!&|,"
        private val OPERATORS = mutableMapOf<String,TokenType>(
            "+" to TokenType.PLUS,
            "-" to TokenType.MINUS,
            "*" to TokenType.STAR,
            "/" to TokenType.SLASH,
            "(" to TokenType.LPAREN,
            ")" to TokenType.RPAREN,
            "{" to TokenType.LBRACE,
            "}" to TokenType.RBRACE,
            "=" to TokenType.EQ,
            "<" to TokenType.LT,
            ">" to TokenType.GT,
            "!" to TokenType.EXCL,
            "&" to TokenType.AMP,
            "|" to TokenType.BAR,
            "==" to TokenType.EQEQ,
            "!=" to TokenType.EXCLEQ,
            "<=" to TokenType.LTEQ,
            ">=" to TokenType.GTEQ,
            "&&" to TokenType.AMPAMP,
            "||" to TokenType.BARBAR,
            "," to TokenType.COMMA,
        )
        private fun isHexNumber(current: Char): Boolean {
            return "abcdef".indexOf(Character.toLowerCase(current)) != -1
        }


    }

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
                // whitespaces
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
        var current = peek(0)
        if (current == '/') {
            if (peek(1) == '/') {
                next()
                next()
                tokenizeComment()
                return
            } else if (peek(1) == '*') {
                next()
                next()
                tokenizeMultilineComment()
                return
            }
        }
        val buffer = StringBuilder()
        while (true) {
            val text = buffer.toString()
            if (!OPERATORS!!.containsKey(text + current) && !text.isEmpty()) {
                addToken(OPERATORS[text])
                return
            }
            buffer.append(current)
            current = next()
        }
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
        when (word) {
            "print" -> addToken(TokenType.PRINT)
            "if" -> addToken(TokenType.IF)
            "else" -> addToken(TokenType.ELSE)
            "while" -> addToken(TokenType.WHILE)
            "for" -> addToken(TokenType.FOR)
            else -> addToken(TokenType.WORD, word)
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

    private fun tokenizeComment() {
        var current = peek(0)
        while ("\r\n\u0000".indexOf(current) == -1) {
            current = next()
        }
    }

    private fun tokenizeMultilineComment() {
        var current = peek(0)
        while (true) {
            if (current == '\u0000') throw RuntimeException("Missing close tag")
            if (current == '*' && peek(1) == '/') break
            current = next()
        }
        next() // *
        next() // /
    }

    private operator fun next(): Char {
        pos++
        return peek(0)
    }

    private fun peek(relativePosition: Int): Char {
        val position = pos + relativePosition
        return if (position >= length) '\u0000' else input[position]
    }

    private fun addToken(type: TokenType?, text: String = "") {
        tokens.add(Token(type, text))
    }

    init {
        length = input.length
        tokens = ArrayList()
    }
}