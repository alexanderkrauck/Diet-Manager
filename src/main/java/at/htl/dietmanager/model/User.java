package at.htl.dietmanager.model;

import at.htl.dietmanager.model.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DM_USER")
public class User {

    @Id
    @Column(name = "U_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "T_ID")
    private Target target;

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
    @Column(name = "U_BIRTHDATE")
    private LocalDate birthDate;


    public User(String userName, String password, String email, float height, float weight, Gender gender, Target target) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.target = target;
    }
    public User(){}

    public int getId() {
        return id;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
