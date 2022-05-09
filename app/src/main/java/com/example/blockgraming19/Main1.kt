package com.example.blockgraming19

import java.io.File
import kotlin.jvm.JvmStatic

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = String(File("C:\\Users\\User\\AndroidStudioProjects\\BlockGraming19\\app\\src\\main\\java\\com\\example\\blockgraming19\\program.txt").readBytes())
        val tokens = Lexer(input).tokenize()
        val program = Parser(tokens).parse()
        println(program.toString())
        program.execute()
    }
}