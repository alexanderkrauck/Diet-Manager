package at.htl.dietmanager.web;

import at.htl.dietmanager.facades.TargetFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.Target;
import at.htl.dietmanager.model.enums.Gender;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserController {
    @Inject
    private UserFacade userFacade;

    @Inject
    private TargetFacade targetFacade;

    private List<Target> targetList;

    private String username = "";
    private String password = "";
    private String email = "";
    private float weight;
    private float height;
    private LocalDate dateOfBirth = LocalDate.now();
    private Gender gender;

    public UserController() {
    }

    @PostConstruct
    public void postConstruct() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (targetList == null)
            targetList = targetFacade.getAllTargets();
    }

    public List<Target> getTargetList() {
        return targetList;
    }

    public void login() {

    }

    public void signUp() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    public void setDateOfBirth(String dateOfBirth) {
        System.out.println(dateOfBirth);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
