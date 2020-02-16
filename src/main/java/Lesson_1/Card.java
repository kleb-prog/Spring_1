package Lesson_1;

public class Card {
    private String patientName;
    private String patientNumber;

    Card() {
        this.patientName = "defaultName";
        this.patientNumber = "defaultNumber";
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }
}
