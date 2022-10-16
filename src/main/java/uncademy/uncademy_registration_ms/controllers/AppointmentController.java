package uncademy.uncademy_registration_ms.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uncademy.uncademy_registration_ms.exceptions.RegistrationNotFoundException;
import uncademy.uncademy_registration_ms.models.Appointment;
import uncademy.uncademy_registration_ms.repositories.AppointmentRepository;

@RestController
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @PostMapping("/CreateAppointment")
    public String createAppointment(@RequestBody Appointment appointment){ 
        appointmentRepository.save(appointment);
        return "Appointment created. id "+ appointment.getId();
    }

    @GetMapping("/Appointment/{id}")
    Appointment getAppointment(@PathVariable String id){
        return appointmentRepository.findById(id)
        .orElseThrow(() -> new RegistrationNotFoundException("No se encontro citacion: " + id));
    }

    @GetMapping("/Appointments")
    List<Appointment> getAppointments(@RequestParam("u") Optional<String> id){
        if(id.isPresent()){
            List<Appointment> listAppointment=this.appointmentRepository.findByRegexIdAppointment(id.get());
            if(listAppointment.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron citas");

            }
            return listAppointment;
        }
        else{
            return appointmentRepository.findAll();
        }
    }
    
}
