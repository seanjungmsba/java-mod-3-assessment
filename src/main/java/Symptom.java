public enum Symptom {

    ATOPY(1, true),
    CYSTITIS(2, true),
    DWARFISM(3, false),
    OSTEOARTHRITIS(4, false),
    EPILEPSY(5, true);

    private int index;
    private boolean curable;

    private Symptom(int i, boolean curable) {
        index = i;
        this.curable = curable;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int idx) {
        this.index = idx;
    }

    public boolean isCurable() {
        return curable;
    }

}
