package at.htl.dietmanager.model;

import at.htl.dietmanager.model.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "DM_USER")
public class User {

    @Id
    @Column(name = "U_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "G_ID")
    private Goal goal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "P_ID")
    private Pal pal;

    @Column(name = "U_USERNAME")
    private String userName;
    @Column(name = "U_PASSWORD")
    private String password;
    @Column(name = "U_EMAIL")
    private String email;
    @Column(name = "U_GENDER")
    private Gender gender;
    @Column(name = "U_HEIGHT")
    private float height;
    @Column(name = "U_WEIGHT")
    private float weight;
    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "U_DATEOFBIRTH")
    private LocalDate dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<EatenFood> eatenFoodList;


    public User(String userName, String password, String email, float height, float weight, Gender gender, Goal goal, Pal pal, LocalDate dateOfBirth) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.goal = goal;
        this.pal = pal;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender isGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate birthDate) {
        this.dateOfBirth = birthDate;
    }


    public float getBMI() {
        return weight / ((float) Math.pow(height / 100, 2));
    }

    public float getDailyCalorieGoal() {
        return (gender == Gender.MALE
                ? 66.47f + (13.7f * weight) + (5f * height) - (6.8f * dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS))
                : 655.1f + (9.6f * weight) + (1.8f * height) - (4.7f * dateOfBirth.until(LocalDate.now(), ChronoUnit.YEARS)))
                * pal.getMultiplier() * goal.getMultiplier();
    }

    public float getTodayEatenCalories() {
        float sum = 0;
        for (EatenFood eatenFood : eatenFoodList) {
            if (eatenFood.getFood() == null)
                sum += eatenFood.getFood().getKcal() * eatenFood.getAmount();
            else
                sum += eatenFood.getAmount();
        }
        return sum;
    }
}
