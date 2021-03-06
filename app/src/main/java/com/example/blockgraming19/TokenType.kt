package com.example.blockgraming19

enum class TokenType {
    //Типы данных
    NUMBER, HEX_NUMBER, WORD, TEXT,
    // Ключевые слова
    PRINT,
    IF,
    ELSE,
    WHILE,
    FOR,
    DO,
    BREAK,
    CONTINUE,
    //Операторы
    PLUS, MINUS, STAR, SLASH, EQ,EQEQ,EXCL,EXCLEQ,
    EOF,LT,LTEQ,GT,GTEQ,
    //Опперанды
    BAR,
    BARBAR,
    AMP,
    AMPAMP,
    //Cкобки
    LPAREN,
    RPAREN,
    LBRACE,
    RBRACE,
    COMMA,
    EQF
}