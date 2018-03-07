package at.htl.dietmanager.model;


import javax.persistence.*;

@Entity
@Table(name = "DM_GOAL")
public class Goal {

    @Id
    @Column(name = "G_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "G_MULTIPLIER")
    private float multiplier;

    @Column(name = "G_DESIGNATION")
    private String designation;

    @Column(name = "G_DESCRIPTION")
    private String description;

    public Goal(float multiplier, String designation, String description) {
        this.multiplier = multiplier;
        this.designation = designation;
        this.description = description;
    }

    public Goal() {
    }

    public int getId() {
        return id;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
