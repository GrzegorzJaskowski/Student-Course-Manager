package com.example.demo.course;

import jakarta.persistence.*;

@Entity
@Table
public class Course {
    @Id
    @SequenceGenerator(
            name = "class_sequence",
            sequenceName = "class_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "class_sequence"
    )
    private Long id;
    private String courseName;
    private Integer maxStudents;

    public Course(){

    }

    public Course(String courseName, Integer maxStudents){
        this.courseName = courseName;
        this.maxStudents = maxStudents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getMaxStudents(){
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents){
        this.maxStudents=maxStudents;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", maxStudents" + maxStudents +
                '}';
    }
}
