package binhle.project.storetech.entity;

import binhle.project.storetech.entity.impo.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class orders {
    //Thông tin chinhc ảu đơn đặt hàng
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name="order_id")
    int id;
    @Column(name="order_date")
    Date order_date;
    @Column(name="toltal_money")
    double toltal_money;
    @Column(name="toltal_status")
    String status;
    @ManyToOne(cascade = CascadeType.ALL)
            @JoinColumn(name="user_id", referencedColumnName = "user_id")
    User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<order_details> order_details;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    payment payment;



}
