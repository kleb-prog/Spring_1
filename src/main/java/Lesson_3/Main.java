package Lesson_3;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("resources/hibernate.cfg.xml")
//                .addAnnotatedClass(Book.class)
//                .addAnnotatedClass(Author.class)
//                .addAnnotatedClass(Reader.class)
                .buildSessionFactory();
    }
}
