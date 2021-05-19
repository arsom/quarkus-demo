package demo.quarkus.services;

import demo.quarkus.entity.Student;
import demo.quarkus.services.repository.StudentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class StudentService {

    @Inject
    StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll().list();
    }

    @Transactional
    public void createStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw  new IllegalStateException("email taken");
        }
        studentRepository.persist(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        var student = studentRepository.findById(studentId);
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }

    @Transactional
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.findById(studentId) != null;
        if(!exists){
            throw  new IllegalStateException("student with id "+ studentId + " does not exists ");
        }
        studentRepository.deleteById(studentId);
    }
}
