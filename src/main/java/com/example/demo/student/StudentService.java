package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentBySurname(student.getSurname());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("student with that surname already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String surname, Double points){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("student with id " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(surname != null && surname.length() > 0 && !Objects.equals(student.getSurname(), surname)){
            Optional<Student> studentOptional = studentRepository.findStudentBySurname(surname);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("student with that surname already exists");
            }
            student.setSurname(surname);
        }

        if(points != null && points >= 0. && !Objects.equals(student.getPoints(), points)){
            student.setPoints(points);
        }
    }

    public List<Student> listAll() {
        return studentRepository.findAll(Sort.by("id").ascending());
    }
}
