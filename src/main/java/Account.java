import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int account_id;
    private int user_id;
    private String account_number;
    private String account_name;
    private String account_type;
    private Integer balance;
    private Date created_at;
   // private Date updated_at;

  /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
   private User user;
*/

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_id=" + user_id +
                ", account_number='" + account_number + '\'' +
                ", account_name='" + account_name + '\'' +
                ", account_type='" + account_type + '\'' +
                ", balance=" + balance +
                ", created_at=" + created_at +

                '}';
    }
}


