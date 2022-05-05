package com.example.blockgraming19

enum class TokenType {
    NUMBER, HEX_NUMBER, WORD, TEXT, BOOLEAN,
    // keyword
    PRINT,
    IF,
    //operators
    PLUS, MINUS, STAR, SLASH, EQ, EOF, LPAREN,  // (
    RPAREN
    // )
}