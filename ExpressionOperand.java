class ExpressionOperand extends ExpressionElement implements Comparable<ExpressionOperand> {
    private final long value;

    public ExpressionOperand(String content) {
        super(content);

        try {
            value = Long.parseLong(content);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException(content + " is a illegal");
        }
    }

    public ExpressionOperand(long value) {
        super(Long.toString(value));
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public int compareTo(ExpressionOperand other) {
        return Long.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        //return Integer.toString(value);
        return content;
    }
}
