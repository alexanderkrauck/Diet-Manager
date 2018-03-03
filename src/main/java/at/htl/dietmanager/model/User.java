package at.htl.dietmanager.model;

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
    @Column(name = "U_SEX")
    private boolean sex;
    @Column(name = "U_HEIGHT")
    private int height;
    @Column(name = "U_WEIGHT")
    private int weight;
    @Column(name = "U_BIRTHDATE")
    private LocalDate birthDate;


    public User(String userName, String password, String email, Target target) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        //this.target = target;
    }
    public User(){}

    public int getId() {
        return id;
    }

    //public Target getTarget() {
    //    return target;
    //}

    //public void setTarget(Target target) {
    //    this.target = target;
    //}

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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
