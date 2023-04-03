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
@Table( name = "Training")
public class Training implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTraining")
    private Integer IdTraining;
    private String Subject;
    private Float Price;
    private String Session;
    private Date TrainStartDate;
    private Date TrainEndtDate;
    private Integer Marks;

    @Override
    public String toString() {
        return "Training{" +
                "IdTraining=" + IdTraining +
                ", Subject='" + Subject + '\'' +
                ", Price=" + Price +
                ", Session='" + Session + '\'' +
                ", TrainStartDate=" + TrainStartDate +
                ", TrainEndtDate=" + TrainEndtDate +
                ", Marks=" + Marks +
                '}';
    }
}
