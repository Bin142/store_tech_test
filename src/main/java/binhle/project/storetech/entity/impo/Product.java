package binhle.project.storetech.entity.impo;

import binhle.project.storetech.entity.cart;
import binhle.project.storetech.entity.category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Entity(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="product_id")
    int id;
    @Column(name="product_name")
    String name;
    @Column(name="product_brand")
    String brand;
    @Column(name="product_price")
    double price;
    @Column(columnDefinition = "TEXT", name="product_description")
    String description;
    @Column(name="product_createdAt")
    Date createdAt;
    @Column(name="product_updatedAt")
    Date updatedAt;
    String imageFileName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    cart cart;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    category category;
}
