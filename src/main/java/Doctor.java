import java.util.List;

public class Doctor {

    private String name;
    private Specialty specialty;

    // added
    private int healingPower;

    public Doctor() {
        this.name = null;
        this.specialty = null;
        this.healingPower = 0;
    }

    public Doctor(String name, Specialty specialty) {
        this.name = name;
        this.specialty = specialty;
        switch(specialty.getIndex()) {
            case 1:
                setHealingPower(5);
                break;
            case 2:
                setHealingPower(8);
                break;
            case 3:
                setHealingPower(15);
                break;
            case 4:
                setHealingPower(10);
                break;
            case 5:
                setHealingPower(7);
                break;
        }
    }

    public String getDoctorName() {
        return this.name;
    }

    public void setDoctorName(String name) {
        this.name = name;
    }

    public Specialty getSpecialty() {
        return this.specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public int getHealingPower() {
        return healingPower;
    }

    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    @Override
    public String toString() {
        return "Doctor: [Name: " + name + ", Specialty: " + specialty + ", Healing Power: " + healingPower + "]";
    }

}
