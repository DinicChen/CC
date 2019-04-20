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
    public static final String MOD = "%";
    public static final String OR = "|";
    public static final String XOR = "^";
    public static final String AND = "&";
    public static final String LEFT_SHIFT = "<<";
    public static final String RIGHT_SHIFT = ">>";

    protected static final Map<String, Integer> operatorPriority = new HashMap<>();

    static {
        operatorPriority.put(OR, 1);
        operatorPriority.put(XOR, 2);
        operatorPriority.put(AND, 3);
        operatorPriority.put(LEFT_SHIFT, 4);
        operatorPriority.put(RIGHT_SHIFT, 4);
        operatorPriority.put(PLUS, 5);
        operatorPriority.put(MINUS, 5);
        operatorPriority.put(MULTIPLE, 6);
        operatorPriority.put(DIVIDE, 6);
        operatorPriority.put(MOD, 6);
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
