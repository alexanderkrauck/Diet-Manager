package at.htl.dietmanager.model;


import javax.persistence.*;

@Entity
@Table(name = "DM_TARGET")
public class Target {

    @Id
    @Column(name = "T_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "T_MULTIPLIER")
    private float multiplier;

    public Target(float multiplier) {
        this.multiplier = multiplier;
    }
    public Target(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }
}
