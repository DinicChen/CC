enum WordSize {
    BYTE(8), WORD(16), DWORD(16), QWORD(64);

    private int wordSize;
    
    private WordSize(int wordSize) {
        this.wordSize = wordSize;
    }

    public int getWordSize() {
        return wordSize;
    }
}
