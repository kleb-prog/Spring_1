package Lesson_1;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
class Reception {

    private Doctor doctor;
    private CardLibrary cardLibrary;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setCardLibrary(CardLibrary cardLibrary) {
        this.cardLibrary = cardLibrary;
    }

    Doctor makeDoctorAppointment() {
        doctor.setCard(cardLibrary.getPatientCard());
        return doctor;
    }
}
