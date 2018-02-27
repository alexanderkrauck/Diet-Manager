package at.htl.dietmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "DM_FOOD")
public class Food {
    @Id
    @Column(name = "F_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "F_NAME")
    private String name;
    @Column(name = "F_DESCRIPTION")
    private String description;


    //per 100g
    @Column(name = "F_KCAL")
    private float kcal;
    @Column(name = "F_FATS")
    private float fats;
    @Column(name = "F_CARBS")
    private float carbs;
    @Column(name = "F_PROTEINS")
    private float proteins;

    public Food() {
    }

    public Food(String name, String description, float kcal, float fats, float carbs, float proteins) {

        this.name = name;
        this.description = description;
        this.kcal = kcal;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getKcal() {
        return kcal;
    }

    public float getFats() {
        return fats;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getProteins() {
        return proteins;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }
}
