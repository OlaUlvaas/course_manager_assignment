package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.*;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {

        int id = StudentSequencer.nextStudentId();

        Course course = new Course(id, courseName, startDate, weekDuration);

        if(course.equals(null)){
            return null;
        }
        // Check for duplicate
        for(Course crs : courses){
            if(crs.getId() == course.getId()){
                return null;
            }
        }
        courses.add(course);
        return course;
    }

    @Override
    public Course findById(int id) {

        Course course = new Course();

        if (id <= 0){
            return null;
        }

        for (Course crs : courses){
            if (crs.getId() == id){
                course = crs;
            }
        }
        return course;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {

        List<Course> courseList = new ArrayList<>();

        if(name.equals(null)){
            return null;
        }

        for(Course crs : courses){
            if(crs.getCourseName().contains(name)){
                courseList.add(crs);
            }
        }
        return courseList;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {

        List<Course> actualCourses = new ArrayList<>();
        LocalDate courseEnd;

        if(end.equals(null)){
            return null;
        }

        for(Course crs : courses){
            courseEnd = crs.getStartDate().plusDays(7 * crs.getWeekDuration());
            if(end.isBefore(courseEnd)|| end.isEqual(courseEnd)){
                actualCourses.add(crs);
            }
        }
        return actualCourses;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {

        List<Course> actualCourses = new ArrayList<>();
        LocalDate courseStart;

        if(start.equals(null)){
            return null;
        }

        for(Course crs : courses){
            courseStart = crs.getStartDate();
            if(start.isAfter(courseStart) || start.isEqual(courseStart)){
                actualCourses.add(crs);
            }
        }
        return actualCourses;
    }

    @Override
    public Collection<Course> findAll() {
        List<Course> allCourses = new ArrayList<>();
        for(Course crs : courses){
            allCourses.add(crs);
        }
        return allCourses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Student student = new Student();
        List<Course> theCourseArray = new ArrayList<>();
        if (studentId <= 0){
            return null;
        }

        for (Course crs : courses) {
            for (Student stn : crs.getStudents()){
                student = stn; // todo: Behövs denna?
                if (student.getId()==studentId){
                    theCourseArray.add(crs);
                }
            }
        }
        return theCourseArray;
    }

    @Override
    public boolean removeCourse(Course course) {
        boolean status = false;
        if(course.equals(null)){
            return false;
        }
        Iterator<Course> iterator = courses.iterator();
        while(iterator.hasNext()){
            Course crs = iterator.next();
            if(crs.equals(course)){
                iterator.remove();
                status = true;
            }
        }
        return status;
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
