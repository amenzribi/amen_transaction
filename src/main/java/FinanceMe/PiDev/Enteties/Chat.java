package FinanceMe.PiDev.Enteties;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "Chat")
public class Chat  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdChat")
    private Integer IdChat;
    private Date DateMsg;
    private String MessageContent;
    private String MessageStatus;
    private String MessageType;
    private String Links;

    @Override
    public String toString() {
        return "Chat{" +
                "IdChat=" + IdChat +
                ", DateMsg=" + DateMsg +
                ", MessageContent='" + MessageContent + '\'' +
                ", MessageStatus='" + MessageStatus + '\'' +
                ", MessageType='" + MessageType + '\'' +
                ", Links='" + Links + '\'' +
                '}';
    }
}
