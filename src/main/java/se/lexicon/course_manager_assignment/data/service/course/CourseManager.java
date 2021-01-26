package se.lexicon.course_manager_assignment.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {

        CourseView cv = new CourseView(form.getId(), form.getCourseName(),
                form.getStartDate(), form.getWeekDuration(),
                converters.studentsToStudentViews(studentDao.findAll()));
        return cv;
    }

    @Override
    public CourseView update(UpdateCourseForm form) {

        Course crs = courseDao.findById(form.getId());
        crs.setCourseName(form.getCourseName());
        crs.setStartDate(form.getStartDate());
        crs.setWeekDuration(form.getWeekDuration());

        CourseView cv = converters.courseToCourseView(crs);

        return cv;
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {

        Collection<Course> courseList = courseDao.findByNameContains(courseName);
        List<CourseView> cvList = converters.coursesToCourseViews(courseList);

        return cvList;
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {

        Collection<Course> courseList = courseDao.findByDateBefore(end);
        List<CourseView> cvList = converters.coursesToCourseViews(courseList);

        return cvList;
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {

        Collection<Course> courseList = courseDao.findByDateAfter(start);
        List<CourseView> cvList = converters.coursesToCourseViews(courseList);

        return cvList;
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {

        boolean addStatus = courseDao.findById(courseId).enrollStudents(studentDao.findById(studentId));

        return addStatus;
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {

        boolean removeStatus = courseDao.findById(courseId).unenrollStudents(studentDao.findById(studentId));

        return removeStatus;
    }

    @Override
    public CourseView findById(int id) {

        Course crs = courseDao.findById(id);
        CourseView cv = converters.courseToCourseView(crs);

        return cv;
    }

    @Override
    public List<CourseView> findAll() {

        Collection<Course> courseList = courseDao.findAll();
        List<CourseView> cvList = converters.coursesToCourseViews(courseList);

        return cvList;
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {

        Collection<Course> courseList = courseDao.findByStudentId(studentId);
        List<CourseView> cvList = converters.coursesToCourseViews(courseList);

        return cvList;
    }

    @Override
    public boolean deleteCourse(int id) {

        boolean deleteStatus = courseDao.removeCourse(courseDao.findById(id));

        return deleteStatus;
    }
}
