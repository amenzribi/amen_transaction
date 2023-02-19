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
@Table( name = "Offer")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOffer")
    private Integer IdOffer;
    private String Subject;
    private Date StartDate;
    private Date EndDate;
    private Float price;
    private String Skills;

    @Override
    public String toString() {
        return "Offer{" +
                "IdOffer=" + IdOffer +
                ", Subject='" + Subject + '\'' +
                ", StartDate=" + StartDate +
                ", EndDate=" + EndDate +
                ", price=" + price +
                ", Skills='" + Skills + '\'' +
                '}';
    }
}
