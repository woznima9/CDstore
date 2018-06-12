package helloworldexample;

import javax.persistence.*;

@Entity
@Table
public class Message implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
