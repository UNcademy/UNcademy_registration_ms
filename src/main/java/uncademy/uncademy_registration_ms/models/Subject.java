package uncademy.uncademy_registration_ms.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Subject {
    @Id
    private String idSubject;
    private String nameSubject;
    private int cupSubject;
    private String days;
    private int time;
    private List<String> requirements;

    public String getIdSubject() {
        return idSubject;
    }
    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }
    public String getNameSubject() {
        return nameSubject;
    }
    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }
    public int getCupSubject() {
        return cupSubject;
    }
    public void setCupSubject(int cupSubject) {
        this.cupSubject = cupSubject;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public List<String> getRequirements() {
        return requirements;
    }
    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    
}


