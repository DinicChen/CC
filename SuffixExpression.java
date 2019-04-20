class SuffixExpression extends Expression {
    private int calculate(ExpressionOperand a, ExpressionOperand b, ExpressionOperator c) {
        switch(c.getContent()) {
            case "+": return a.getValue() + b.getValue();
            case "-": return a.getValue() - b.getValue();
            case "×": return a.getValue() * b.getValue();
            case "÷": return a.getValue() / b.getValue();
            case "%": return a.getValue() % b.getValue();
            case "|": return a.getValue() | b.getValue();
            case "^": return a.getValue() ^ b.getValue();
            case "&": return a.getValue() & b.getValue();
            case "<<": return a.getValue() << b.getValue();
            case ">>": return a.getValue() >> b.getValue();
            default: return 0;
        }
    }

    @Override
    public int getResultValue() throws Exception {
        SimpleStack<ExpressionOperand> scalc = new SimpleStack<>();

        for(ExpressionElement e : expression) {
            if(e instanceof ExpressionOperand)
                scalc.push((ExpressionOperand)e);
            else if(e instanceof ExpressionOperator) {
                ExpressionOperator operator = (ExpressionOperator) e;
                ExpressionOperand opf = scalc.pop();
                ExpressionOperand ops = scalc.pop();
                ExpressionOperand temp = null;

                if(opf == null || ops == null)
                    throw new Exception("expression is illegal");

                temp = new ExpressionOperand(calculate(ops, opf, operator));
                scalc.push(temp);
            }
        }

        if(scalc.size() != 1)
            throw new Exception("expression is illegal");

        return scalc.pop().getValue();
    }
}
