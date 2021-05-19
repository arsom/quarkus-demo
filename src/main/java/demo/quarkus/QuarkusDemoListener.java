package demo.quarkus;

import demo.quarkus.config.ConfigResource;
import demo.quarkus.entity.Student;
import demo.quarkus.services.repository.StudentRepository;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Slf4j
@ApplicationScoped
public class QuarkusDemoListener {

    @Inject
    ConfigResource configResource;

    @Inject
    StudentRepository studentRepository;

    void onStart(@Observes StartupEvent ev) {
       if(configResource.initialData){
           Student lek = new Student("Lek","arsomm@gmail.com", LocalDate.of(1991, Month.APRIL,11));
           Student sai = new Student("sai","sai@gmail.com", LocalDate.of(1991, Month.AUGUST,19));
           studentRepository.addAll(List.of(lek,sai));
       }
   }
}
