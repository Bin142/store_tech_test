package binhle.project.storetech.entity.impo;

import binhle.project.storetech.entity.cart;
import binhle.project.storetech.entity.orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    int id;

    @NotBlank(message ="Fullname not empty!!!")
    @Size(min = 4, max = 40, message = "fullname at least 4 character and not more than 40 character!!!")
    @Column(name="user_fullname")
    String fullname;

    @NotBlank(message ="Username not empty!!!")
    @Size(min = 4, max = 30, message = "username at least 4 character and not more than 30 character!!!")
    @Column(name="user_username")
    String username;

    @NotBlank(message ="Fullname not empty!!!")
    @Size(min = 4, message = "fullname at least 4 character and not more than 30 character!!!")
    @Column(name="user_password")
    String password;

    @NotBlank(message ="Email not empty!!!")
    @Column(name="user_email")
    @Size(min = 10)
    String email;

    @NotBlank(message ="Email not empty!!!")
    @Size(min = 8, message = "Phonenumber at least 8 number and no more than 12 number!!!")
    @Column(name="user_phone_number")
    String phonenumber;

    @NotBlank(message ="Address not empty!!!")
    @Column(name="user_address")
    String address;

    @Column(name="user_age")
    int age;

    private Date createdAt;

//    @Column(name="user_image_file_name")
//    String imageFileName;

    @ElementCollection
    @Column(name="role")
    Set<String> roles;

    @OneToOne(mappedBy = "user")
    cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<orders> order;



//    @OneToMany(mappedBy = "user")
//    oders oders;


}
