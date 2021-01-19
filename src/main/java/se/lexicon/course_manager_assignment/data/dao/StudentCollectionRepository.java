package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.controller.StudentController;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.student.StudentManager;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        StudentSequencer.getStudentSequencer();
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        Student student = new Student(name, email, address);
        students.add(student);
        return student; // todo: ska studenten in i listan el ej?
    }
    @Override
    public Student findByEmailIgnoreCase(String email) {
        Iterator <Student> iterator = students.iterator();
        while(iterator.hasNext()){
            Student student = iterator.next();

            String ignoreCaseEmail = email;
            ignoreCaseEmail.toLowerCase();

            if(student.equals(students.contains(email))){
                return student;
            }
        }
        /*for(int i = 0; i < students.size(); i++){
            if(){

            }
        }*/
        // todo:

        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        return null;
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return null;
    }

    @Override
    public boolean removeStudent(Student student) {
        return false;
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
    }
}
