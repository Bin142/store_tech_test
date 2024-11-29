package binhle.project.storetech.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class order_details {
    //chi tiết từng sản phẩm trong 1 đơn hàng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_detail_id")
    int id;

    @Column(name="order_quantity")
    int quantity;
    @Column(name="order_unit_price")
    double unit_price;//đơn giá của mỗ sản phẩm
    @Column(name="order_total_money")
    double total_money;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", columnDefinition = "order_id")
    orders order;




}
