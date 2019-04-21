import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class Expression implements Iterable<ExpressionElement> {
    private int radix = 10;
    protected final List<ExpressionElement> expression = new ArrayList<>();

    public int getRadix() {
        return radix;
    }

    public void setRadix(int radix) {
        this.radix = radix;
    }

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
            case ExpressionElement.MOD:
                expression.add(ExpressionOperator.OP_MOD);
                break;
            case ExpressionElement.OR:
                expression.add(ExpressionOperator.OP_OR);
                break;
            case ExpressionElement.XOR:
                expression.add(ExpressionOperator.OP_XOR);
                break;
            case ExpressionElement.AND:
                expression.add(ExpressionOperator.OP_AND);
                break;
            case ExpressionElement.LEFT_SHIFT:
                expression.add(ExpressionOperator.OP_LEFT_SHIFT);
                break;
            case ExpressionElement.RIGHT_SHIFT:
                expression.add(ExpressionOperator.OP_RIGHT_SHIFT);
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

            if(radix != 10 && e instanceof ExpressionOperand)
                sb.append(Integer.toString(((ExpressionOperand)e).getValue(), radix).toUpperCase());
            else
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
