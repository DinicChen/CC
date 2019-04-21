class ExpressionOperand extends ExpressionElement implements Comparable<ExpressionOperand> {
    private final int value;

    public ExpressionOperand(String content) {
        super(content);

        try {
            value = Integer.parseInt(content);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException(content + " is a illegal");
        }
    }

    public ExpressionOperand(int value) {
        super(Integer.toString(value));
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(ExpressionOperand other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        //return Integer.toString(value);
        return content;
    }
}
