package at.htl.dietmanager.web;

import at.htl.dietmanager.facades.GoalFacade;
import at.htl.dietmanager.facades.PalFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.Goal;
import at.htl.dietmanager.model.Pal;
import at.htl.dietmanager.model.User;
import at.htl.dietmanager.model.enums.Gender;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class SignUpController {

    @Inject
    private UserFacade userFacade;

    @Inject
    private GoalFacade goalFacade;

    @Inject
    private PalFacade palFacade;

    private List<Goal> goalList;
    private List<Pal> palList;

    private String username = "";
    private String password = "";
    private String email = "";
    private float weight;
    private float height;
    private LocalDate dateOfBirth = new Date(System.currentTimeMillis() - (long) 1000 * (long) 60 * (long) 60 * (long) 24 * (long) 365 * (long) 18).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    private Gender gender;
    private Goal selectedGoal = null;
    private Pal selectedPal = null;
    private String selectedPalString = null;
    private String selectedGoalString = null;

    private Date minDate = new Date(System.currentTimeMillis() - (long) 1000 * (long) 60 * (long) 60 * (long) 24 * (long) 365 * (long) 120);
    private Date maxDate = new Date(System.currentTimeMillis() - (long) 1000 * (long) 60 * (long) 60 * (long) 24 * (long) 365 * (long) 8);

    public SignUpController() {
    }

    @PostConstruct
    public void postConstruct() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (goalList == null)
            goalList = goalFacade.getAllGoals();
        if (palList == null)
            palList = palFacade.getAllPals();
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

    public List<Pal> getPalList() {
        return palList;
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

    public Date getDateOfBirth() {
        return Date.from(dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth.getTime() <= maxDate.getTime() && dateOfBirth.getTime() >= minDate.getTime())
            this.dateOfBirth = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getGender() {
        return gender == Gender.MALE ? "m" : "f";
    }

    public void setGender(String gender) {
        this.gender = gender.equals("m") ? Gender.MALE : Gender.FEMALE;
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    public void createUser() {
        userFacade.addUser(new User(username, password, email, height, weight, gender, selectedGoal, selectedPal, dateOfBirth));

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Diet-Manager/faces/home.xhtml");

        } catch (IOException ex) {

        }
    }

    public Date getMinDate() {
        return minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public Goal getSelectedGoal() {
        return selectedGoal == null ? (selectedGoal = goalList.get(0)) : selectedGoal;
    }

    public String getSelectedGoalString() {
        return selectedGoal == null ? (selectedGoal = goalList.get(0)).getDesignation() : selectedGoal.getDesignation();
    }

    public void setSelectedGoalString(String selectedTarget) {
        for (Goal target : goalList) {
            if (target.getId() == Integer.parseInt(selectedTarget)) {
                this.selectedGoal = target;
                break;
            }
        }
    }

    public Pal getSelectedPal() {
        return selectedPal == null ? (selectedPal = palList.get(0)) : selectedPal;
    }

    public String getSelectedPalString() {
        return selectedPal == null ? (selectedPal = palList.get(0)).getDesignation() : selectedPal.getDesignation();
    }

    public void setSelectedPalString(String selectedPal) {
        for (Pal pal : palList) {
            if (pal.getId() == Integer.parseInt(selectedPal)) {
                this.selectedPal = pal;
                break;
            }
        }
    }
}
