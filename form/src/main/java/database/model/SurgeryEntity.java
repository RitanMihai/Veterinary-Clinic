package database.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalTime;

@Entity
@Table(name = "surgery", schema = "public", catalog = "veterinary_clinic")
public class SurgeryEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "time", nullable = false)
    private LocalTime time;
    @Basic
    @Column(name = "minimum_price", nullable = false, precision = 0)
    private BigInteger minimumPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigInteger getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(BigInteger minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
