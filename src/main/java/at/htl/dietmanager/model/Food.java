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
        this(name, description, kcal);
        this.name = name;
        this.description = description;
        this.kcal = kcal;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
    }

    public Food(String name, String description, float fats, float carbs, float proteins) {
        this(name, fats, carbs, proteins);
        this.description = description;
    }

    public Food(String name, String description, float kcal) {
        this(name, kcal);
        this.description = description;
    }

    public Food(String name, float kcal) {
        this.name = name;
        this.kcal = kcal;
    }

    public Food(String name, float fats, float carbs, float proteins) {
        this.name = name;
        this.fats = fats;
        this.carbs = carbs;
        this.proteins = proteins;
        this.kcal = 9.3f * fats + 4.1f * carbs + 4.1f * proteins;
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
