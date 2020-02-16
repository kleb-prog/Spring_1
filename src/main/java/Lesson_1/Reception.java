package Lesson_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("reception")
class Reception {

    private Doctor doctor;
    private CardLibrary cardLibrary;

    @Autowired
    @Qualifier("therapist")
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Autowired
    public void setCardLibrary(CardLibrary cardLibrary) {
        this.cardLibrary = cardLibrary;
    }

    Doctor makeDoctorAppointment() {
        doctor.setCard(cardLibrary.getPatientCard());
        return doctor;
    }
}
