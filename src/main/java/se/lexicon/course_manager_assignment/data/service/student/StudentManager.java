package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentCollectionRepository;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {

        StudentView sv = new StudentView(form.getId(), form.getName(),
                form.getEmail(), form.getAddress());

        return sv;
    }

    @Override
    public StudentView update(UpdateStudentForm form) {

        Student student = studentDao.findById(form.getId());

        student.setName(form.getName());
        student.setEmail(form.getEmail());
        student.setAddress(form.getAddress());

        StudentView sv = converters.studentToStudentView(student);

        return sv;
    }

    @Override
    public StudentView findById(int id) {

        Student student = studentDao.findById(id);
        StudentView sv = converters.studentToStudentView(student);

        return sv;
    }

    @Override
    public StudentView searchByEmail(String email) {

        Student student = studentDao.findByEmailIgnoreCase(email);
        StudentView sv = converters.studentToStudentView(student);

        return sv;
    }

    @Override
    public List<StudentView> searchByName(String name) {

        Collection<Student> studentList = studentDao.findByNameContains(name);
        List<StudentView> svList = converters.studentsToStudentViews(studentList);

        return svList;
    }

    @Override
    public List<StudentView> findAll() {

        Collection<Student> studentList = studentDao.findAll();
        List<StudentView> svList = converters.studentsToStudentViews(studentList);

        return svList;
    }

    @Override
    public boolean deleteStudent(int id) {

        boolean deleteStatus = studentDao.removeStudent(studentDao.findById(id));

        return deleteStatus;
    }
}
