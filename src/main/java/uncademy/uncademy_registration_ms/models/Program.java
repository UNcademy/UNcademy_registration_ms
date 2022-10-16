package uncademy.uncademy_registration_ms.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document
public class Program {

    @Id
    private String idProgram;
    //private String nameProgram;
    private ArrayList<String> subjects; 


    public void setIdProgram(String idProgram){
        this.idProgram=idProgram;

    }
    public String getIdProgram(){
        return idProgram;
    }

    public void setSubjects(ArrayList<String> subjects){
        this.subjects=subjects;

    }
    public ArrayList<String> getSubjects(){
        return subjects;
    }
    
}
