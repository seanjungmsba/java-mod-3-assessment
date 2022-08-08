package hospital;

public class Patient {

    private String name;
    private Ailment ailment;
    private int healthIndex;
    private boolean dead;
    private boolean healed;

    public Patient() {
    }

    public Patient(String name, Ailment ailment) {
        this.name = name;
        this.ailment = ailment;
        healthIndex = ailment.getStartingHealthIndex();
    }

    public String getName() {
        return name;
    }

    public Ailment getAilment() {
        return ailment;
    }

    public int getHealthIndex() {
        return healthIndex;
    }

    public void setHealthIndex(int modHealthIndex) {
        healthIndex += modHealthIndex;
        if (healthIndex <= 0) dead = true;
        if (healthIndex >= 100) healed = true;
    }

    public boolean isDead() {
        return healthIndex <= 0;
    }

    public boolean isHealed() {
        return healthIndex >= 100;
    }

    public String printPatient() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(System.getProperty("line.separator"));
        sb.append("       ");
        for (int i = 0; i < healthIndex / 10; i++) {
            sb.append("=");
        }
        sb.append(" " + healthIndex + "% Health");
        return sb.toString();
    }
}