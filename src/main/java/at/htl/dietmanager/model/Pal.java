package at.htl.dietmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "DM_PAL")
public class Pal {
    @Id
    @Column(name = "P_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "P_DESIGNATION")
    private String designation;

    @Column(name = "P_DESCRIPTION")
    private String description;

    @Column(name = "P_MULTIPLIER")
    private float multiplier;

    public Pal() {
    }

    public Pal(float multiplier, String designation, String description) {
        this.multiplier = multiplier;
        this.designation = designation;
        this.description = description;
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
