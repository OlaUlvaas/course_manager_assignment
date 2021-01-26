package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;
    private StudentDao testStudentObject;

    Collection<Student> studentList = new ArrayList<>();

    Student student1 = new Student(1,"Ola", "ola@home.se", "Hemma 1");
    Student student2 = new Student(2,"Olaus", "olaus@work.se", "Hemma 2");
    Student student3 = new Student(3,"Olof", "olof@mail.nu", "Hemma 3");
    Student student4 = new Student(4,"Odin", "odin@home.se", "Hemma 4");

    Collection<Course> courseList = new ArrayList<>();

    LocalDate date1 = LocalDate.of(2020, 12, 7);
    LocalDate date2 = LocalDate.of(2021, 2, 7);
    LocalDate date3 = LocalDate.of(2021, 4, 7);
    LocalDate date4 = LocalDate.of(2020, 6, 7);


    Course course1 = new Course(1, "Java", date1, 32);
    Course course2 = new Course(2, "Python", date2, 8);
    Course course3 = new Course(3, "C#", date3, 15);
    Course course4 = new Course(4, "C++", date4, 20);

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    // Test createCourse Method from Course Collection Repository
    // public Course createCourse(String courseName, LocalDate startDate, int weekDuration)
    @Test
    public void test_create_course_search_for_id_4(){

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        assertEquals(course4, testObject.findById(4));
    }

    // Still Testing createCourse Method from Course Collection Repository
    // public Course createCourse(String courseName, LocalDate startDate, int weekDuration)
    public void test_create_course_fail(){

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        assertFalse(course3.equals(testObject.findById(2)));
    }
    // Test findById Method
    // public Course findById(int id)
    @Test
    public void find_course_by_id(){

        Course expectedCourse = course3;

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        Course actualCourse = testObject.findById(3);

        assertEquals(expectedCourse, actualCourse);
    }


    // Test findByNameContains Method
    // public Collection<Course> findByNameContains(String name)
    @Test
    public void test_find_by_name_contains(){

        List<Course> cCourses = new ArrayList<>();
        cCourses.add(course3);
        cCourses.add(course4);

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        Collection<Course> actualCourses = testObject.findByNameContains("C");
        assertEquals(cCourses, actualCourses);
    }
    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
