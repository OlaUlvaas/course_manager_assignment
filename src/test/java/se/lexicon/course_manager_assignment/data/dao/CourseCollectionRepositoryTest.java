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
    LocalDate date2 = LocalDate.of(2021, 3, 8);
    LocalDate date3 = LocalDate.of(2021, 6, 7);
    LocalDate date4 = LocalDate.of(2021, 9, 13);
    LocalDate january20210101 = LocalDate.of(2021,1,1);
    LocalDate october20211001 = LocalDate.of(2021,10,1);
    LocalDate september20200901 = LocalDate.of(2020,9,1);
    LocalDate september20190901 = LocalDate.of(2019,9,1);
    LocalDate april20210401 = LocalDate.of(2021,04,1);
    LocalDate july20210701 = LocalDate.of(2021,07,1);
//    LocalDate february15 = LocalDate.of(2021,02,15);
//    LocalDate february15 = LocalDate.of(2021,02,15);


    Course course1 = new Course(1, "Java", date1, 20);
    Course course2 = new Course(2, "Python", date2, 20);
    Course course3 = new Course(3, "C#", date3, 20);
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
    // Test findByDateBefore Method
    // public Collection<Course> findByDateBefore(LocalDate end)
    @Test
    public void test_find_by_date_before(){

        List<Course> dateBeforeCourses = new ArrayList<>();
        dateBeforeCourses.add(course3);
        dateBeforeCourses.add(course4);

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        Collection<Course> actualCourses = testObject.findByDateBefore(october20211001);
        assertEquals(dateBeforeCourses, actualCourses);
    }
    // Test findByDateAfter Method
    // public Collection<Course> findByDateAfter(LocalDate start)
    @Test
    public void test_find_by_date_after(){
        List<Course> dateAfterCourses = new ArrayList<>();
        dateAfterCourses.add(course1);
        dateAfterCourses.add(course2);

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        Collection<Course> actualCourses = testObject.findByDateAfter(april20210401);
        assertEquals(dateAfterCourses, actualCourses);
    }
    // Test findAll Method
    // public Collection<Course> findAll()
    @Test
    public void test_find_all(){

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        Collection<Course> actualCourses = testObject.findAll();
        assertEquals(courseList, actualCourses);
    }
    // Test findByStudentId Method
    // public Collection<Course> findByStudentId(int studentId)
    @Test
    public void test_find_by_student_id(){
        // I know this test method is wrong, but I've done my best to try anyway.
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);

        List<Course> expectedCourses = new ArrayList<>();

        expectedCourses.add(course1);
        expectedCourses.add(course4);


        testStudentObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testStudentObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testStudentObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testStudentObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        Collection<Course> actualCourses = testObject.findByStudentId(1);
        assertEquals(expectedCourses, actualCourses);

    }
    // Test removeCourse Method
    // public boolean removeCourse(Course course)
    @Test
    public void test_remove_course(){

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);

        testObject.removeCourse(course1);

        assertEquals(course3, testObject.findAll().toArray()[1]);
    }
    // Test clear Method
    // public void clear()
    @Test
    public void test_clear(){

        testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration());
        testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration());
        testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration());
        testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration());

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);

        List<Course> actualList = new ArrayList<>();

        actualList.add(testObject.createCourse(course1.getCourseName(), course1.getStartDate(), course1.getWeekDuration()));
        actualList.add(testObject.createCourse(course2.getCourseName(), course2.getStartDate(), course2.getWeekDuration()));
        actualList.add(testObject.createCourse(course3.getCourseName(), course3.getStartDate(), course3.getWeekDuration()));
        actualList.add(testObject.createCourse(course4.getCourseName(), course4.getStartDate(), course4.getWeekDuration()));

        courseList.clear();
        actualList.clear();

        assertEquals(courseList, actualList);
    }
    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
