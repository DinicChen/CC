class ExpressionOperand extends ExpressionElement implements Comparable<ExpressionOperand> {
    private final long value;

    public ExpressionOperand(String content) {
        super(content);

        try {
            value = long.parseInt(content);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException(content + " is a illegal");
        }
    }

    public ExpressionOperand(int value) {
        super(Long.toString(value));
        this.value = value;
    }

    public long getValue() {
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
