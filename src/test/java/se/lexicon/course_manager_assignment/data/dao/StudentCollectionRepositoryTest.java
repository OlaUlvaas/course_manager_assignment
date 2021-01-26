package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    Collection<Student> studentList = new ArrayList<>();

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
    public void test_create_student_search_for_id_4() {

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        assertEquals(student4, testObject.findById(4));
    }
    // Still Testing Create Method from Student Collection Repository
    // public Student createStudent(String name, String email, String address)
    @Test
    public void test_create_student_fail(){

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        assertFalse(student3.equals(testObject.findById(2)));
    }
    // Test findByEmailIgnoreCase Method
    // public Student findByEmailIgnoreCase(String email)
    @Test
    public void test_find_by_email(){

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        assertEquals(student1, testObject.findByEmailIgnoreCase(student1.getEmail()));
    }
    @Test
    public void test_find_by_name_contains(){

        List<Student> olaStudents = new ArrayList<>();
        olaStudents.add(student1);

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        Collection<Student> actualStudents = testObject.findByNameContains("Ola");

        assertEquals(olaStudents, actualStudents);
    }
    // Test findById Method
    // public Student findById(int id)
    @Test
    public void test_find_by_id(){

        Student expectedStudent = student3;

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        Student actualStudent = testObject.findById(student3.getId());
        assertEquals(expectedStudent, actualStudent);
    }
    // Test findAll Method
    // public Collection<Student> findAll()
    @Test
    public void test_find_all(){

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        Collection<Student> actualStudents = testObject.findAll();

        assertEquals(studentList, actualStudents);
    }
    // Test removeStudent Method
    // public boolean removeStudent(Student student)
    @Test
    public void test_remove_student(){

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        testObject.removeStudent(student1);

        assertEquals(student4, testObject.findAll().toArray()[2]);
    }

    // Test clear Method
    // public void clear()
    @Test
    public void test_clear(){

        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress());
        testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress());
        testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress());

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        List<Student> actualList = new ArrayList<>();

        actualList.add(testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress()));
        actualList.add(testObject.createStudent(student2.getName(), student2.getEmail(), student2.getAddress()));
        actualList.add(testObject.createStudent(student3.getName(), student3.getEmail(), student3.getAddress()));
        actualList.add(testObject.createStudent(student4.getName(), student4.getEmail(), student4.getAddress()));

        studentList.clear();
        actualList.clear();

        assertEquals(studentList, actualList);



    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }

}
