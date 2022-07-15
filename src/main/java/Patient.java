public class Patient {

    private String name;
    private Symptom symptom;

    // added
    private int healthIndex;

    public Patient() {
        this.name = null;
        this.symptom = null;
    }

    public Patient(String name, Symptom symptom) {
        this.name = name;
        this.symptom = symptom;
        switch (symptom.getIndex()) {
            case 1:
                setHealthIndex(70);
                break;
            case 2:
                setHealthIndex(55);
                break;
            case 3:
                setHealthIndex(15);
                break;
            case 4:
                setHealthIndex(30);
                break;
            case 5:
                setHealthIndex(40);
                break;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symptom getSymptom() {
        return this.symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public int getHealthIndex() {
        return this.healthIndex;
    }

    public void setHealthIndex(int healthIndex) {
        this.healthIndex += healthIndex;
    }

    public void treated(int treatment) {
        this.healthIndex += treatment;
    }

    public boolean healed() {
        return this.healthIndex >= 100;
    }

    public boolean died() {
        return this.healthIndex <= 0;
    }

    @Override
    public String toString() {
        return "Patient: [Name: " + name + ", Symptom: " + symptom + ", Health Index: " + healthIndex + "]";
    }

}
