package demo.quarkus.resource;

import demo.quarkus.entity.Student;
import demo.quarkus.services.StudentService;
import demo.quarkus.services.repository.StudentRepository;
import io.quarkus.hibernate.orm.panache.Panache;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Path("student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentService studentService;

    @GET
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @POST
    public void createStudent(Student student){
        studentService.createStudent(student);
    }

    @PUT
    @Path("{studentId}")
    public void updateStudent(@PathParam("studentId") Long studentId,
                              @NotNull @QueryParam("name") String name,
                              @NotNull @QueryParam("email") String email){
        studentService.updateStudent(studentId,name,email);
    }

    @DELETE
    @Path("{studentId}")
    public void deleteStudent(@PathParam("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

}
