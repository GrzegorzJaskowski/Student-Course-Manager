package com.example.demo.course;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        Optional<Course> courseOptional = courseRepository.findCourseByCourseName(course.getCourseName());
        if(courseOptional.isPresent()){
            throw new IllegalStateException("course with that surname already exists");
        }
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId){
        boolean exists = courseRepository.existsById(courseId);
        if(!exists){
            throw new IllegalStateException("course with id " + courseId + " does not exist");
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String courseName, Integer maxStudents){
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new IllegalStateException("course with id " + courseId + " does not exist"));

        if(courseName != null && courseName.length() > 0 && !Objects.equals(course.getCourseName(), courseName)){
            Optional<Course> courseOptional = courseRepository.findCourseByCourseName(courseName);
            if(courseOptional.isPresent()){
                throw new IllegalStateException("course with that surname already exists");
            }
            course.setCourseName(courseName);
        }

        if(maxStudents != null && maxStudents >= 0 && !Objects.equals(course.getMaxStudents(), maxStudents)){
            course.setMaxStudents(maxStudents);
        }
    }
}
