package uncademy.uncademy_registration_ms.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import uncademy.uncademy_registration_ms.models.Program;

@Repository
public interface ProgramRepository extends MongoRepository<Program,String>{
    @Query("{'idProgram':{'$regex':?0,'$options':'i'}}")
    Program findByRegexProgram(String idProgram);

    @Query("{'idProgram':{'$regex':?0,'$options':'i'}}")
    List<Program> findByRegexIdProgram(String idProgram);
}
