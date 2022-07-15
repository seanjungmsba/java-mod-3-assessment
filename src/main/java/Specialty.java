public enum Specialty {

    DERMATOLOGY(1),
    UROLOGY(2),
    PEDIATRICS(3),
    ORTHOPEDICS(4),
    NEUROLOGY(5);

    private int index;

    private Specialty(int i) {
        index = i;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int idx) {
        this.index = idx;
    }

}