package ioservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import hospital.Hospital;

import java.io.File;
import java.io.FileWriter;

public class JsonIOService {

    private final String fileName;

    public JsonIOService(String fileName) {
        this.fileName = fileName;
    }

    public boolean fileExists() {
        return (new File(fileName)).exists();
    }

    public Hospital readFromFile() {
        Hospital hospital = null;
        try {
            hospital = new ObjectMapper().readValue(new File(fileName), Hospital.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hospital;

    }

    public void writeToFile(Hospital hospital) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName, true);
            String json = new ObjectMapper().writeValueAsString(hospital);
            fileWriter.write(json);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetFile() {
        try {
            new FileWriter(fileName, false).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}