public class Patient {

    private String name;
    private Symptom symptom;

    // added
    private double healthIndex;

    public Patient() {
        this.name = null;
        this.symptom = null;
    }

    public Patient(String name, Symptom symptom) {
        this.name = name;
        this.symptom = symptom;
        switch (symptom.getIndex()) {
            case 1:
                setHealthIndex(70.0);
                break;
            case 2:
                setHealthIndex(55.0);
                break;
            case 3:
                setHealthIndex(15.0);
                break;
            case 4:
                setHealthIndex(30.0);
                break;
            case 5:
                setHealthIndex(40.0);
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

    public double getHealthIndex() {
        return this.healthIndex;
    }

    public void setHealthIndex(double healthIndex) {
        this.healthIndex += healthIndex;
    }

    public void treated(int treatment) {
        this.healthIndex += treatment;
    }

    public boolean healed() {
        return this.healthIndex >= 100.0;
    }

    public boolean died() {
        return this.healthIndex <= 0.0;
    }

    @Override
    public String toString() {
        return "Patient: [Name: " + name + ", Symptom: " + symptom + ", Health Index: " + healthIndex + "]";
    }

}
