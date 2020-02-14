package Lesson_1;

import org.springframework.stereotype.Component;

@Component("cardLibrary")
class CardLibrary {

    Card getPatientCard() {
        return new Card();
    }
}
