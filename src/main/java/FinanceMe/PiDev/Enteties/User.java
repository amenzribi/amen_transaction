package FinanceMe.PiDev.Enteties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "User")

public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Integer idUser;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private String PlaceBirth;
    private String fnc;
    private String Gender;
    private String Adress;
    private Integer PostalCode;
    private String Email;
    private String PhoneNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Account> Account;
    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", BirthDate=" + BirthDate +
                ", PlaceBirth='" + PlaceBirth + '\'' +
                ", Function='" + fnc + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Adress='" + Adress + '\'' +
                ", PostalCode=" + PostalCode +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }
}