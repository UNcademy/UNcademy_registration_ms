package uncademy.uncademy_registration_ms.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document
public class Appointment {

    @Id
    private String id;
    private String idStudent;
    private String idProgram;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date date;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date dateEnd;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdStudent() {
        return idStudent;
    }
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
    public String getIdProgram() {
        return idProgram;
    }
    public void setIdProgram(String idProgram) {
        this.idProgram = idProgram;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public void setTime(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
}
