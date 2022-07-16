import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void checkIfSymptomIsProperlyReturned() {
        Patient sean = new Patient("Sean", Symptom.ATOPY);
        Symptom seanSymptom = sean.getSymptom();
        Symptom expectedSymptom = Symptom.ATOPY;
        assertEquals(seanSymptom, expectedSymptom);
    }

    @Test
    void checkIfHealthIndexIsProperlyReturned() {
        Patient david = new Patient("David", Symptom.DWARFISM);
        double davidHealthIndex = david.getHealthIndex();
        double expectedHealthIndex = 15.0;
        assertEquals(davidHealthIndex, expectedHealthIndex);
    }

    @Test
    void checkIfHealthIndexIsProperlyChanged() {
        Patient haley = new Patient("Haley", Symptom.EPILEPSY);
        haley.setHealthIndex(30.0);
        double haleyHealthIndex = haley.getHealthIndex();
        double expectedHealthIndex = 70.0;
        assertEquals(haleyHealthIndex, expectedHealthIndex);
    }

}