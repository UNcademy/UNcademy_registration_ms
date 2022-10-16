package uncademy.uncademy_registration_ms.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document()
public class Subject {
    @Id
    private String idSubject;
    private String nameSubject;
    private int cupSubject;
    private String days;
    private int time;
    private ArrayList<String> requirements;/* */

/* 
   @PersistenceConstructor
    public Subject(String idSubject, String nameSubject, int codSubject, int cupSubject, String days, int time,
        ArrayList<String> requirements) {
    this.idSubject = idSubject;
    this.nameSubject = nameSubject;
    this.codSubject = codSubject;
    this.cupSubject = cupSubject;
    this.days = days;
    this.time = time;
    this.requirements = requirements;
}
*/

    public void setIdSubject(String idSubject){
        this.idSubject=idSubject;

    }
    public String getIdSubject(){
        return idSubject;
    }
    /////////////////////////////////////
    

    public void setNameSubject(String nameSubject){
        this.nameSubject=nameSubject;

    }
    public String getNameSubject(){
        return nameSubject;
    }
//////////////////////////////////////////////////
    public void setCupSubject(int cupSubject){
        this.cupSubject=cupSubject;

    }
    public int getCupSubject(){
        return cupSubject;
    }
////////////////////////////////////////////////////
    public void setDaysSubject(String days){
        this.days=days;

    }
    public String getDaysSubject(){
        return days;
    }
///////////////////////////////////////////////////////
    public void setTimeSubject(int time){
        this.time=time;

    }
    public int getTimeSubject(){
        return time;
    }
////////////////////////////////////////////
     public void setrequirementsSubject(ArrayList<String> requirements){
        this.requirements=requirements;

    }
    public ArrayList<String> setrequirementsSubject(){
        return requirements;
    }

    
}


