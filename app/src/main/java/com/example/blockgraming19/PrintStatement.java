package com.example.blockgraming19;
public final class PrintStatement implements Statement {
    
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        System.out.print(expression.eval());
    }

    @Override
    public String toString() {
        return "print " + expression;
    }
}
