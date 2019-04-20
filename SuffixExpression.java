class SuffixExpression extends Expression {
    private int doPlus(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() + b.getValue();
    }

    private int doMinus(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() - b.getValue();
    }

    private int doMultiple(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() * b.getValue();
    }

    private int doDivide(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() / b.getValue();
    }

    private int doMod(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() % b.getValue();
    }

    private int doOr(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() | b.getValue();
    }

    private int doXor(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() ^ b.getValue();
    }

    private int doAnd(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() & b.getValue();
    }

    private int doLeftShift(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() << b.getValue();
    }

    private int doRightShift(ExpressionOperand a, ExpressionOperand b) {
        return a.getValue() >> b.getValue();
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

                if(operator.equals(ExpressionOperator.OP_PLUS))
                    temp = new ExpressionOperand(doPlus(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_MINUS))
                    temp = new ExpressionOperand(doMinus(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_MULTIPLE))
                    temp = new ExpressionOperand(doMultiple(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_DIVIDE))
                    temp = new ExpressionOperand(doDivide(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_MOD))
                    temp = new ExpressionOperand(doMod(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_OR))
                    temp = new ExpressionOperand(doOr(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_XOR))
                    temp = new ExpressionOperand(doXor(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_AND))
                    temp = new ExpressionOperand(doAnd(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_LEFT_SHIFT))
                    temp = new ExpressionOperand(doLeftShift(ops, opf));
                else if(operator.equals(ExpressionOperator.OP_RIGHT_SHIFT))
                    temp = new ExpressionOperand(doRightShift(ops, opf));

                scalc.push(temp);
            }
        }

        if(scalc.size() != 1)
            throw new Exception("expression is illegal");

        return scalc.pop().getValue();
    }
}
