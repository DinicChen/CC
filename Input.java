import java.util.ArrayList;

public class Input {
    private StringBuilder operand;
    private InfixExpression expression;

    public Input() {
        operand = new StringBuilder();
        expression = new InfixExpression();
    }

    public void setOperand(String content) {
        operand.append(content);
    }

    public int getOperand() {
        try {
            return Integer.parseInt(operand.toString());
        }
        catch(NumberFormatException e) {
            return 0;
        }
    }

    private String getOperator(String content) throws IllegalArgumentException {
        switch(content) {
            case "+": return "+";
            case "-": return "-";
            case "*":
            case "×": return "×";
            case "/":
            case "÷": return "÷";
            case "%":
            case "Mod": return "%";
            case "|":
            case "Or": return "‖";
            case "^":
            case "Xor": return "^";
            case "&":
            case "And": return "&";
            case "Lsh": return "<<";
            case "Rsh": return ">>";
            case "(": return "(";
            case ")": return ")";
            default: throw new IllegalArgumentException();
        }
    }
    
    public void appendOperator(String content) {
        if(operand.length() == 0)
            return;

        try {
            String operator = getOperator(content);
            expression.append(operand.toString());
            operand = new StringBuilder();
            expression.append(operator);
        }
        catch(IllegalArgumentException e) {
        }
    }

    public void backspace() {
        if(operand.length() != 0)
            operand.deleteCharAt(operand.length() - 1);
    }

    public void clear() {
        expression.clear();
        operand = new StringBuilder();
    }

    public void clearEntry() {
        operand = new StringBuilder();
    }

    public void switchSign() {
        if(operand.length() == 0) 
            return;

        if(operand.charAt(0) != '-') 
            operand.insert(0, '-');
        else
            operand.deleteCharAt(0);
    }

    public void not() {
        if(operand.length() == 0) 
            return;
        
        int op;
        try {
            op = Integer.parseInt(operand.toString());
        }
        catch(NumberFormatException e) {
            op = 0;
        }

        op = ~op;
        operand = new StringBuilder(op + "");
    }

    public String getExpression() {
        return expression.toString();
    }

    public void getResult() {
        if(operand.length() != 0)
            expression.append(operand.toString());

        int result;
        try {
            result = expression.getResultValue();
        }
        catch(Exception e) {
            result = 0;
        }

        operand = new StringBuilder(result + "");
        expression.clear();
    }
}
