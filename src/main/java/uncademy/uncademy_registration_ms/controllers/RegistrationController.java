package uncademy.uncademy_registration_ms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uncademy.uncademy_registration_ms.exceptions.RegistrationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uncademy.uncademy_registration_ms.models.Registration;
import uncademy.uncademy_registration_ms.models.Subject;
import uncademy.uncademy_registration_ms.repositories.RegistrationRepository;
import uncademy.uncademy_registration_ms.repositories.SubjectRepository;

@RestController
public class RegistrationController {
   
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    SubjectRepository subjectRepository;
    

    @PostMapping("/CreateRegistration")
    public Registration createRegistration(@RequestBody Registration registration){
        registration.getSubjects().clear();
        return registrationRepository.save(registration);
    }

    @GetMapping("/Registrations")
    List<Registration> getRegistrations(@RequestParam("u") Optional<String> idStudent){
        if(idStudent.isPresent()){
            List<Registration> listRegistrations=this.registrationRepository.findByRegexIdRegistrations(idStudent.get());
            if(listRegistrations.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron inscripciones");

            }
            return listRegistrations;
        }
        else{
            return registrationRepository.findAll();
        }
    }
    @GetMapping("/Registration/{idStudent}")
    Registration getRegistration(@PathVariable String idStudent){
        Optional<Registration> data=registrationRepository.findById(idStudent);
        System.out.println(data.get().getSubjects());
        return registrationRepository.findById(idStudent)
        .orElseThrow(() -> new RegistrationNotFoundException("No se encontro un registro: " + idStudent));
    }

    Subject subject(String id){

        return null;
    }

    @PutMapping("UpdateRegistration/{idStudent}")
    public Registration putRegistration(@PathVariable String idStudent, @RequestBody Registration registration){
        Optional<Registration> registrationData = registrationRepository.findById(idStudent);
        if(!registrationData.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontra la registro con id: "+idStudent);
        }else{
            Registration _Registration=registrationData.get();
            if(registration.getSubjects().isEmpty()){
                _Registration.getSubjects().clear();
            }else{

                ArrayList<String> auxArrayListRegistro=new ArrayList<>();
                ArrayList<String> auxArrayListData=new ArrayList<>();
    
                System.out.println("1 "+auxArrayListData.size()+" "+auxArrayListRegistro.size());

                for (int j=0; j<registration.getSubjects().size();j++) {
                    auxArrayListRegistro.add(registration.getSubjects().get(j).getIdSubject());
                }
                for (int j=0; j<registrationData.get().getSubjects().size();j++) {
                    auxArrayListData.add(registrationData.get().getSubjects().get(j).getIdSubject());
                }
                //System.out.println("1 "+auxArrayListData.size()+" "+auxArrayListRegistro.size());
                //System.out.println("1 "+registrationData.get().getSubjects().size()+" "+registration.getSubjects().size());

                for (int j=0; j<auxArrayListRegistro.size();j++) {
                    System.out.println("1 "+auxArrayListData.size()+" "+auxArrayListRegistro.size());

                    System.out.println("1 "+registrationData.get().getSubjects().size()+" "+registration.getSubjects().size());

                   // Optional<Subject> subject=subjectRepository.findById(registration.getSubjects().get(j).getIdSubject());
                    if(!auxArrayListData.contains(auxArrayListRegistro.get(j))){
                        String idSubject=auxArrayListRegistro.get(j);
                        Optional<Subject> subject=subjectRepository.findById(idSubject);
                        if(!subject.isPresent()){
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontro materia: "+registration.getSubjects().get(j).getNameSubject());
                        }else{
                            if(subject.get().getCupSubject()>0){
                                _Registration.getSubjects().add(subject.get());
                                auxArrayListData.add(subject.get().getIdSubject());
                            }else{
                                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay cupos disṕonibles en materia: "+subject.get().getNameSubject());
                            }
                        }
                    }
                }
                for (int j=0; j<registrationData.get().getSubjects().size();j++){
                    if(!auxArrayListRegistro.contains(auxArrayListData.get(j))){
                        _Registration.getSubjects().remove(_Registration.getSubjects().get(j));
                    }


                }
            }
            registrationRepository.save(_Registration);
            return _Registration;
        }
        
    }

}
