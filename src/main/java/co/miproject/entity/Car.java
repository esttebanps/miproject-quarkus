package co.miproject.entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="table_car")
public class Car extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String brand;
    public int year;
    public float price;
}
