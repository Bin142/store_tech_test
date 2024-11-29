package binhle.project.storetech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String fullname;
    String email;
    String phonenumber;
    String note;
    Date created_at;
    Date update_at;

}
