package uncademy.uncademy_registration_ms.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uncademy.uncademy_registration_ms.models.Program;
import uncademy.uncademy_registration_ms.repositories.ProgramRepository;

@RestController
public class ProgramController {

    @Autowired
    ProgramRepository programRepository;

    @PostMapping("/CreateProgram")
	public Program createNotificacion(@RequestBody Program program) {
		return programRepository.save(program);
	}

    @GetMapping("/Programs")
    List<Program> getSuscripciones(@RequestParam("u") Optional<String> idProgram){
        if(idProgram.isPresent()){
            List<Program> listPrograms=this.programRepository.findByRegexIdProgram(idProgram.get());
            if(listPrograms.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontraron programas");

            }
            return listPrograms;
        }
        else{
            return programRepository.findAll();
        }
    }
    
}
