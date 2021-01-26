package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.controller.StudentController;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.data.service.student.StudentManager;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.*;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {

        int id = StudentSequencer.nextStudentId();

        Student student = new Student(id, name, email, address);

        if(student.equals(null)){ //todo: kanske "==" istället?
            return null;
        }
        // Check for duplicates
        for(Student stn : students){
            if(stn.getEmail() == student.getEmail()){
                return null;
            }
        }
        students.add(student);
        return student;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {

        if(email.equals(null)){
            return null;
        }

//        Student student = new Student();
        Student student = null;

        for(Student stn : students){
            if(stn.getEmail().equalsIgnoreCase(email)){
                student = stn;
                return student;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {

        List<Student> studentList = new ArrayList<>();

        if(name.equals(null)){
            return null;
        }

        for(Student stn : students){
            if(stn.getName().contains(name)){
                studentList.add(stn);
                return studentList;
            }
        }
        return null;
    }

    @Override
    public Student findById(int id) {

        Student student = new Student();

        if (id <= 0){
            return null;
        }

        for (Student stn : students){
            if (stn.getId() == id){
                student = stn;
                return student;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findAll() {

        List<Student> allStudent = new ArrayList<>();

        for(Student stn : students){
            allStudent.add(stn);
        }
        return allStudent;
    }

    @Override
    public boolean removeStudent(Student student) {

        boolean status = false;

        if(student.equals(null)){
            return false;
        }

        Iterator<Student> iterator = students.iterator();

        while(iterator.hasNext()){
            Student stn = iterator.next();
            if(stn.equals(student)){
                iterator.remove();
                status = true;

            }
        }
        return status;
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
    }
}
