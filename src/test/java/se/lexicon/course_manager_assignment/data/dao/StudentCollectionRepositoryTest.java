package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    Student student1 = new Student(1,"Ola", "ola@home.se", "Hemma 1");
    Student student2 = new Student(2,"Olaus", "olaus@work.se", "Hemma 2");
    Student student3 = new Student(3,"Olof", "olof@mail.nu", "Hemma 3");
    Student student4 = new Student(4,"Odin", "odin@home.se", "Hemma 4");

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    // Test Create Method from Student Collection Repository
    // public Student createStudent(String name, String email, String address)
    @Test
    public void test_create_student_search_for_id_4 () {

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        assertEquals(student4, testObject.findById(4));
    }
    // Still Testing Create Method from Student Collection Repository
    // public Student createStudent(String name, String email, String address)
    @Test
    public void test_create_student_fail (){

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        assertFalse(student3.equals(testObject.findById(2)));
    }
    // Test findByEmailIgnoreCase Method (String email)
    // public Student findByEmailIgnoreCase(String email)

    @Test
    public void test_find_by_email (){

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

//        Student expectedStudent = new Student(4,"Odin", "odin@home.se", "Hemma 4");
        //Student actualStudent = testObject.findByEmailIgnoreCase("odin@home.se");

        assertEquals(student1, testObject.findByEmailIgnoreCase(student1.getEmail()));
        //assertEquals(student4);
    }
    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }

}
