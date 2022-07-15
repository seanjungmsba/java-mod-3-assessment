public enum Symptom {

    ATOPY(1),
    CYSTITIS(2),
    DWARFISM(3),
    OSTEOARTHRITIS(4),
    EPILEPSY(5);

    private int index;

    private Symptom(int i) {
        index = i;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int idx) {
        this.index = idx;
    }

}
