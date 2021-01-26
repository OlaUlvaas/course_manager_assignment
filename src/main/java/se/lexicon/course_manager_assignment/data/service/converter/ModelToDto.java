package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;
//import sun.jvm.hotspot.utilities.GenericArray;


import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ModelToDto implements Converters {


    @Override
    public StudentView studentToStudentView(Student student) {
        if(student.equals(null)){
            return null;
        }
        StudentView sv = new StudentView(student.getId(), student.getName(),
                student.getEmail(), student.getAddress());
        return sv;
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        if(course.equals(null)){
            return null;
        }
        CourseView cv = new CourseView(course.getId(), course.getCourseName(),
                course.getStartDate(), course.getWeekDuration(), studentsToStudentViews(course.getStudents()));
        return cv;
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        if(courses.equals(null)){
            return null;
        }
        List<CourseView> cvList = new ArrayList<>();
        for(Course crs : courses){
            cvList.add(courseToCourseView(crs));
        }
        return cvList;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        if(students.equals(null)){
            return null;
        }
        List<StudentView> svList = new ArrayList<>();
        for(Student stn : students){
            svList.add(studentToStudentView(stn));
        }
        return svList;
    }
}
