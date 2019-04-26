enum Radix {
    BINARY(2), OCTAL(8), DECIMAL(10), HEXADECIMAL(16);

    private int radix;
    
    private Radix(int radix) {
        this.radix = radix;
    }

    public int getRadix() {
        return radix;
    }
}
