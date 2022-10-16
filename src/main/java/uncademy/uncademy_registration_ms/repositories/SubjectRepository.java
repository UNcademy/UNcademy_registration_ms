package uncademy.uncademy_registration_ms.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import uncademy.uncademy_registration_ms.models.Subject;


@Repository
public interface SubjectRepository extends MongoRepository<Subject,String>{

    @Query("{'idSubject':{'$regex':?0,'$options':'i'}}")
    List<Subject> findByRegexIdSubject(String idSubject);
    @Query("{'idSubject':{'$regex':?0,'$options':'i'}}")
    Subject findByRegexSubject(String idSubject);

}
