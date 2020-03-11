package Lesson_7.controllers;

import Lesson_7.entities.Course;
import Lesson_7.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/**")
@RestController
public class RestCourseController {
    private CoursesService coursesService;

    @Autowired
    public void setCoursesService(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return coursesService.getAllCoursesList();
    }

    @GetMapping("/course/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return coursesService.getCourseById(courseId);
    }

    @PostMapping("/course")
    public Course addCourse(@RequestBody Course theCourse) {
        return coursesService.addOrUpdateCourse(theCourse);
    }

    @PutMapping(path = "/course", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Course updateCourse(Course theCourse) {
        return coursesService.addOrUpdateCourse(theCourse);
    }

    @DeleteMapping("/course/{courseId}")
    public int deleteCourse(@PathVariable Long courseId) {
        coursesService.removeCourse(courseId);
        return HttpStatus.OK.value();
    }
}
