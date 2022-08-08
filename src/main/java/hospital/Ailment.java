package hospital;

public enum Ailment {
    RASH(90, Specialty.DERMATOLOGY, true),
    SCARRING(80, Specialty.DERMATOLOGY, false),
    MUSCLE_ATROPHY(85, Specialty.PHYSICAL_MEDICINE, true),
    PARKINSON(60, Specialty.NEUROLOGY, false),
    BRAIN_TUMOR(40, Specialty.NEUROLOGY, true),
    BULLET_WOUND(30, Specialty.SURGERY, true),
    BLOCKED_ARTERY(10, Specialty.SURGERY, true);

    private final int startingHealthIndex;
    private final Specialty specialty;
    private final boolean isCurable;

    Ailment(int startingHealthIndex, Specialty specialty, boolean isCurable) {
        this.startingHealthIndex = startingHealthIndex;
        this.specialty = specialty;
        this.isCurable = isCurable;
    }

    public int getStartingHealthIndex() {
        return startingHealthIndex;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public boolean isCurable() {
        return isCurable;
    }
}