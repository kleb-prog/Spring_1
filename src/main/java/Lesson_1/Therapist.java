package Lesson_1;

import org.springframework.stereotype.Component;

@Component("therapist")
public class Therapist implements Doctor {
    private Card card;

    @Override
    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public void visitDoctor() {
        if (card != null) {
            System.out.println("You have visited the therapist!");
        } else {
            System.out.println("You forgot the card!");
        }
    }
}
