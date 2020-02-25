package Lesson_1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

//        --== AppConfig ==--
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Reception reception = context.getBean("reception", Reception.class);
        Doctor doctor = reception.makeDoctorAppointment();
        doctor.visitDoctor();


//      --== XML Spring ==--
//        ApplicationContext context = new ClassPathXmlApplicationContext("resources/config.xml");
//        Reception reception = context.getBean("reception", Reception.class);
//        Doctor doctor = reception.makeDoctorAppointment();
//        doctor.visitDoctor();
//
//        Reception reception2 = context.getBean("reception", Reception.class);
//        Doctor doctor2 = reception2.makeDoctorAppointment();
//        doctor2.visitDoctor();
//        System.out.println(doctor == doctor2);

//        --== Without Spring ==--
//        Reception reception = new Reception();
//        reception.setCard(new CardLibrary());
//        reception.setDoctor(new Dentist());
//        Doctor doctor = reception.makeDoctorAppointment();
//        doctor.visitDoctor();
    }
}
