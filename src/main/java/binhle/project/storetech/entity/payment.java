package binhle.project.storetech.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Random;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name="payment_id")
    int id;

    @Column(name="transaction_id", nullable = false)
    int transaction_id = new Random().nextInt();

    @Column(name="payment_created_at")
    Date created_at;

    @OneToOne(fetch = FetchType.LAZY)
            @JoinColumn(name="order_id", referencedColumnName = "order_id")
    orders order;

    @Column(name="payment_status")
    @ElementCollection
    Set<String> payments;


}
