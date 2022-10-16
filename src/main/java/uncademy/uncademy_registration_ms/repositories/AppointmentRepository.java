package uncademy.uncademy_registration_ms.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import uncademy.uncademy_registration_ms.models.Appointment;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    @Query("{'id:{'$regex':?0,'$options':'i'}}")
    Appointment findByRegexAppointment(String id);

    @Query("{'id:{'$regex':?0,'$options':'i'}}")
    List<Appointment>  findByRegexIdAppointment(String id); 
    
}