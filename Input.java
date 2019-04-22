import java.util.ArrayList;

public class Input {
    private int radix = 10;
    private String lastOperator = "";
    private StringBuilder operand = new StringBuilder();
    private InfixExpression expression = new InfixExpression();

    public void setRadix(int radix) {
        int currentOperand = getOperand();

        if(currentOperand != 0)
            operand = new StringBuilder(Integer.toString(currentOperand, radix));

        this.radix = radix;
        expression.setRadix(radix);
    }

    public void setOperand(String content) {
        operand.append(content);
        lastOperator = "";
    }

    public int getOperand() {
        if(operand.length() == 0)
            return 0;

        try {
            return Integer.parseInt(operand.toString(), radix);
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
            case "Or": return "|";
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
        try {
            String operator = getOperator(content);
            
            if(operator.equals(")") &&
                    !(lastOperator.equals("") ||
                     lastOperator.equals(")") ||
                     expression.canAppendClosingBrace()))
                throw new IllegalArgumentException();
            else if(!lastOperator.equals("") &&
                    !lastOperator.equals(")") &&
                    !operator.equals("(")) 
                expression.pop();

            lastOperator = operator;
            
            if(operand.length() != 0) {
                expression.append(Integer.toString(getOperand()));
                operand = new StringBuilder();
            }

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
        
        int currentOperand;
        try {
            currentOperand = Integer.parseInt(operand.toString(), radix);
        }
        catch(NumberFormatException e) {
            currentOperand = 0;
        }

        currentOperand = ~currentOperand;
        operand = new StringBuilder(Integer.toString(currentOperand, radix));
    }

    public String getExpression() {
        return expression.toString();
    }

    public void getResult() {
        lastOperator = "";

        if(operand.length() != 0)
            expression.append(Integer.toString(getOperand()));

        int result;
        try {
            result = expression.getResultValue();
        }
        catch(Exception e) {
            result = 0;
        }

        operand = new StringBuilder(Integer.toString(result, radix));
        expression.clear();
    }
}
