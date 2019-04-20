import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class ExpressionElement {
    public static final String LEFT_PARENTHESES = "(";
    public static final String RIGHT_PARENTHESES = ")";

    protected static boolean isLegalDelimeter(String content) {
        if(LEFT_PARENTHESES.equals(content) || RIGHT_PARENTHESES.equals(content))
            return true;
        return false;
    }

    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLE = "×";
    public static final String DIVIDE = "÷";

    protected static final Map<String, Integer> operatorPriority = new HashMap<>();

    static {
        operatorPriority.put(PLUS, 1);
        operatorPriority.put(MINUS, 1);
        operatorPriority.put(MULTIPLE, 2);
        operatorPriority.put(DIVIDE, 2);
    }

    protected static boolean isLegalOperator(String content) {
        if(!operatorPriority.containsKey(content))
            return false;
        return true;
    }

    protected static boolean isLegalOperand(String content) {
        Pattern numberPat = Pattern.compile("(\\+|-)?(\\d+\\.)?\\d+");
        Matcher mat = numberPat.matcher(content);
        if(!mat.matches())
            return false;
        return true;
    }

    protected final String content;

    protected ExpressionElement(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
