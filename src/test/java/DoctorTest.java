import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    @Test
    void healingPowerProperlyReturned() {
        Doctor sean = new Doctor("Sean", Specialty.DERMATOLOGY);
        double seanHealingPower = sean.getHealingPower();
        double expectedHealingPower = 5.0;
        assertEquals(seanHealingPower, expectedHealingPower);
    }

    @Test
    void healingPowerProperlyChanged() {
        Doctor sean = new Doctor("Sean", Specialty.DERMATOLOGY);
        sean.setHealingPower(10.0);
        double seanHealingPower = sean.getHealingPower();
        double expectedHealingPower = 10.0;
        assertEquals(seanHealingPower, expectedHealingPower);
    }

    @Test
    void specialtyProperlyReturned() {
        Doctor sean = new Doctor("Sean", Specialty.DERMATOLOGY);
        Specialty seanSpecialty = sean.getSpecialty();
        Specialty expectedSpecialty = Specialty.DERMATOLOGY;
        assertEquals(seanSpecialty, expectedSpecialty);
    }

}