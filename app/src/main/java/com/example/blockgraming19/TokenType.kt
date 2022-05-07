package com.example.blockgraming19

enum class TokenType {
    //Типы данных
    NUMBER, HEX_NUMBER, WORD, TEXT, BOOLEAN,
    // Ключевые слова
    PRINT,
    IF,
    ELSE,
    //Операторы
    PLUS, MINUS, STAR, SLASH, EQ, EOF,LT,GT, LPAREN,
    RPAREN
}