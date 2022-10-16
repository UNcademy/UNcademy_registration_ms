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
        System.out.println(data.get());
        return registrationRepository.findById(idStudent)
        .orElseThrow(() -> new RegistrationNotFoundException("No se encontro un registro: " + idStudent));


    }

    

    @PutMapping("UpdateRegistration/{idStudent}")
    public Registration putRegistration(@PathVariable String idStudent, @RequestBody Registration registration){
        //id Body
        //System.out.print("registrationRepository "+registrationRepository.findById(registration.getIdStudent()));
        Optional<Registration> registrationData = registrationRepository.findById(idStudent);
        
        
        if(!registrationData.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontra la registro con id: "+idStudent);
        }else{
                Registration _configuracion=registrationData.get();
                //System.out.println("size inicial: "+registrationData.get().getProgram().contains("100"));
                //System.out.println("hhhhhhhhhhhhhhhh: "+_configuracion.getProgram());
                if(registration.getSujects().isEmpty()){
                    _configuracion.getSujects().clear();

                }else{
                    for (int j=0; j<registration.getSujects().size();j++) { 
                        if(!registrationData.get().getSujects().contains(registration.getSujects().get(j))) {
                            String materia=registration.getSujects().toString();
                            Optional<Subject> subject=subjectRepository.findById(materia);
                            //System.out.print("materiaaaaaaa "+subject.get().getNameSubject()+" "+subject.get().getCupSubject());
                            if(subject.get().getCupSubject()>0){
                                _configuracion.getSujects().add(registration.getSujects().get(j));
                            }else{
                                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay cupos disṕonibles");
                            }
                        }

                    }

                    for(int i=0;i<registrationData.get().getSujects().size();i++){
                        //System.out.println("Dato "+i+" " +registrationData.get().getSujects().get(i));
                        if(!registration.getSujects().contains(_configuracion.getSujects().get(i))){

                            _configuracion.getSujects().remove(_configuracion.getSujects().get(i));
                            //System.out.println("SE elimino :  "+registrationData.get().getProgram().get(i));
                        }
                    }


                }

               registrationRepository.save(_configuracion);
        
                return _configuracion;
            
        }        

    }

}
