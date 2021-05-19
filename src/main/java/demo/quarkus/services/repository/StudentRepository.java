package demo.quarkus.services.repository;

import demo.quarkus.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student,Long> {

    public Optional<Student> findStudentByEmail(String email){
        return find("select s from Student s where s.email = ?1 ",email).stream().findFirst();
    }

    @Transactional
    public void addAll(List<Student> students){
        persist(students);
    }

}
