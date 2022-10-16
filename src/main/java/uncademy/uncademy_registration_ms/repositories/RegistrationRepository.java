package uncademy.uncademy_registration_ms.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import uncademy.uncademy_registration_ms.models.Registration;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {
    @Query("{'idStudent:{'$regex':?0,'$options':'i'}}")
    Registration findByRegexRegistration(String idStudent);

    @Query("{'idStudent:{'$regex':?0,'$options':'i'}}")
    List<Registration>  findByRegexIdRegistrations(String idStudent); 
    
}
