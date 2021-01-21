package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {
        if(student.equals(null)){
            throw new IllegalArgumentException("Student Object is empty");
        }
        StudentView sv = new StudentView(student.getId(), student.getName(),
                student.getEmail(), student.getAddress());
        return sv;
    }

    @Override
    public CourseView courseToCourseView(Course course) {
        if(course.equals(null)){
            throw new IllegalArgumentException("Course Object is empty");
        }
        CourseView cv = new CourseView(course.getId(), course.getCourseName(),
                course.getStartDate(), course.getWeekDuration(), studentsToStudentViews(course.getStudents()));
        return cv;
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        if(courses.equals(null)){
            throw new IllegalArgumentException("Courses Objects is empty");
        }
        List<CourseView> cvList = new ArrayList<>();

        return null;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        return null;
    }
}
