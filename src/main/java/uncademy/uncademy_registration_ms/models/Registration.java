package uncademy.uncademy_registration_ms.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Registration {


    @Id
    private String idStudent;
    private String idProgram;
   @Field("idSubject")
	@DBRef
    private ArrayList<Subject> subjects;

    public void setIdStudent(String idStudent){
        this.idStudent=idStudent;

    }
    public String getIdStudent(){
        return idStudent;
    }
    public void setIdProgram(String idProgram){
        this.idProgram=idProgram;

    }
    public String getIdProgram(){
        return idProgram;
    }

    public void setSubjects(ArrayList<Subject> subjects){
        this.subjects=subjects;

    }
    public ArrayList<Subject> getSujects(){
        return subjects;
    }
    @Override
    public String toString() {
        return "Registration [ idStudent=" + idStudent + ", subjects=" + subjects + "]";
    }

    
}
