class InfixExpression extends Expression {
    public SuffixExpression toSuffixExpression() {
        SuffixExpression suffix = new SuffixExpression();
        SimpleStack<ExpressionElement> sop = new SimpleStack<>();

        for(ExpressionElement e : expression) {
            if(e instanceof ExpressionOperand)
                suffix.append(e);
            else if(e instanceof ExpressionDelimeter) {
                if(e.equals(ExpressionDelimeter.DM_LEFT_PARENTHESES))
                    sop.push(e);
                else if(e.equals(ExpressionDelimeter.DM_RIGHT_PARENTHESES)) {
                    while(!sop.isEmpty() && !sop.peek().equals(ExpressionDelimeter.DM_LEFT_PARENTHESES))
                        suffix.append(sop.pop());

                    if(!sop.isEmpty())
                        sop.pop();
                }
            }
            else if(e instanceof ExpressionOperator) {
                while(!sop.isEmpty() && sop.peek() instanceof ExpressionOperator && 0 >= ((ExpressionOperator)e).compareTo((ExpressionOperator)sop.peek()))
                    suffix.append(sop.pop());

                sop.push(e);
            }
        }

        while(!sop.isEmpty())
            suffix.append(sop.pop());

        return suffix;
    }

    @Override
    public int getResultValue() throws Exception {
        return toSuffixExpression().getResultValue() ;
    }
}
