import java.util.List;

public class Doctor {

    private String name;
    private Specialty specialty;

    // added
    private double healingPower;

    public Doctor() {
        this.name = null;
        this.specialty = null;
        this.healingPower = 0.0;
    }

    public Doctor(String name, Specialty specialty) {
        this.name = name;
        this.specialty = specialty;
        switch(specialty.getIndex()) {
            case 1:
                setHealingPower(5.0);
                break;
            case 2:
                setHealingPower(8.0);
                break;
            case 3:
                setHealingPower(0.00001);
                break;
            case 4:
                setHealingPower(0.000001);
                break;
            case 5:
                setHealingPower(7.0);
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

    public double getHealingPower() {
        return healingPower;
    }

    public void setHealingPower(double healingPower) {
        this.healingPower = healingPower;
    }

    @Override
    public String toString() {
        return "Doctor: [Name: " + name + ", Specialty: " + specialty + ", Healing Power: " + healingPower + "]";
    }

}
