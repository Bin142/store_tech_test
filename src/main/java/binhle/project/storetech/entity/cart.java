package binhle.project.storetech.entity;

import binhle.project.storetech.entity.impo.Product;
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
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    int id;
    @Column(name="cart_quantity")
    int quantity=0;
    @Column(name="cart_created_at")
    Date created_at;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
            @JoinColumn(name="user_id", referencedColumnName = "user_id")
    User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name="product_id")
    Set<Product> product;





}
