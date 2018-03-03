package at.htl.dietmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DM_EATEN_FOOD")
public class EatenFood {

    @Id
    @Column(name = "EF_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "EF_AMOUNT")
    private int amount;

    @Column(name = "EF_EATEN_DATE_TIME")
    private LocalDateTime eatenDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "F_ID")
    private Food food;

    public EatenFood() {
    }

    public EatenFood(int amount, LocalDateTime eatenDateTime, Food food) {
        this.amount = amount;
        this.eatenDateTime = eatenDateTime;
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
