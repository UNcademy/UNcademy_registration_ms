package uncademy.uncademy_registration_ms.controllers;

import java.util.List;
import java.util.Optional;

import uncademy.uncademy_registration_ms.repositories.SubjectRepository;
import uncademy.uncademy_registration_ms.exceptions.RegistrationNotFoundException;
import uncademy.uncademy_registration_ms.models.Subject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;




@RestController
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository ){
        this.subjectRepository =  subjectRepository;

    }

    @PostMapping("/CreateSubject")
    public Subject createSubject(@RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    @GetMapping("/Subject/{idSubject}")
    Subject getSubject(@PathVariable String idSubject){
        Optional<Subject> data=subjectRepository.findById(idSubject);
        System.out.println(data.get());
        return subjectRepository.findById(idSubject)
        .orElseThrow(() -> new RegistrationNotFoundException("No se encontro materia: " + data.get().getNameSubject()));
    }

    @GetMapping("/Subjects")
    List<Subject> getSuscripciones(@RequestParam("u") Optional<String> idSubject){
        if(idSubject.isPresent()){
            List<Subject> listSuscripcion=this.subjectRepository.findByRegexIdSubject(idSubject.get());
            if(listSuscripcion.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron asignaturas");

            }
            return listSuscripcion;
        }
        else{
            return subjectRepository.findAll();
        }
    
    }


}
