class ExpressionDelimeter extends ExpressionElement {
    public static final ExpressionDelimeter DM_LEFT_PARENTHESES = new ExpressionDelimeter(ExpressionElement.LEFT_PARENTHESES);
    public static final ExpressionDelimeter DM_RIGHT_PARENTHESES = new ExpressionDelimeter(ExpressionElement.RIGHT_PARENTHESES);

    private ExpressionDelimeter(String content) {
        super(content);

        if(!isLegalDelimeter(content))
            throw new IllegalArgumentException("Delimeter " + content + " is illegal");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj.getClass() != this.getClass())
            return false;

        ExpressionDelimeter other = (ExpressionDelimeter) obj;

        return content.equals(other.content);
    }
}
