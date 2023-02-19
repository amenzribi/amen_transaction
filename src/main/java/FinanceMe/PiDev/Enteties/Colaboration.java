package FinanceMe.PiDev.Enteties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "Colaboration")
public class Colaboration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdColaboration")
    private Integer IdColaboration;
    private String Name;
    private String Adress;
    private String City;
    private Integer Phone;
    private String Email;

    @Override
    public String toString() {
        return "Colaboration{" +
                "IdColaboration=" + IdColaboration +
                ", Name='" + Name + '\'' +
                ", Adress='" + Adress + '\'' +
                ", City='" + City + '\'' +
                ", Phone=" + Phone +
                ", Email='" + Email + '\'' +
                '}';
    }
}
