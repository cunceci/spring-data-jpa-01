package pe.edu.cibertec.spring_data_jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;
    private String firstName;
    private String lastName;
    private Integer addressId;
    private String picture;
    private String email;
    private Integer storeId;
    private Integer active;
    private String username;
    private String password;
    private Date lastUpdate;

}
