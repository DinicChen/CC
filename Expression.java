import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class Expression implements Iterable<ExpressionElement> {
    protected final List<ExpressionElement> expression = new ArrayList<>();

    public boolean append(ExpressionElement e) {
        if(e == null) 
            return false;
        
        expression.add(e);

        return true;
    }

    public boolean append(String content) {
        switch(content) {
            case ExpressionElement.LEFT_PARENTHESES:
                expression.add(ExpressionDelimeter.DM_LEFT_PARENTHESES);
                break;
            case ExpressionElement.RIGHT_PARENTHESES:
                expression.add(ExpressionDelimeter.DM_RIGHT_PARENTHESES);
                break;
            case ExpressionElement.PLUS:
                expression.add(ExpressionOperator.OP_PLUS);
                break;
            case ExpressionElement.MINUS:
                expression.add(ExpressionOperator.OP_MINUS);
                break;
            case ExpressionElement.MULTIPLE:
                expression.add(ExpressionOperator.OP_MULTIPLE);
                break;
            case ExpressionElement.DIVIDE:
                expression.add(ExpressionOperator.OP_DIVIDE);
                break;
            default:
                try {
                    ExpressionOperand operand = new ExpressionOperand(content);
                    expression.add(operand);
                }
                catch(Exception e) {
                    return false;
                }
        }

        return true;
    }

    @Override
    public String toString() {
        boolean firstAdd = true;
        StringBuilder sb = new StringBuilder();

        for(ExpressionElement e : expression) {
            if(!firstAdd)
                sb.append(" ");
            else
                firstAdd = false;

            sb.append(e.toString());
        }

        return sb.toString();
    }

    @Override
    public Iterator<ExpressionElement> iterator() {
        return expression.iterator();
    }

    public void clear() {
        expression.clear();
    }

    public abstract int getResultValue() throws Exception;
}
