package binhle.project.storetech.entity;

import binhle.project.storetech.entity.impo.Product;
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
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="category_id")
    int id;
    @Column(name="category_name")
    String name;
    @Column(name="category_total_product")
    int total_product;
    @Column(name="category_created_at")
    Date created_at;
    @Column(name="category_updated_at")
    Date updated_at;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Product> product;



}
