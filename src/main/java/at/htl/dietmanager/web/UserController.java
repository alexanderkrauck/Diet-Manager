package at.htl.dietmanager.web;

import at.htl.dietmanager.facades.EatenFoodFacade;
import at.htl.dietmanager.facades.FoodFacade;
import at.htl.dietmanager.facades.GoalFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.EatenFood;
import at.htl.dietmanager.model.User;
import org.primefaces.model.chart.PieChartModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserController {
    @Inject
    private UserFacade userFacade;

    @Inject
    private GoalFacade goalFacade;

    @Inject
    private FoodFacade foodFacade;

    @Inject
    private EatenFoodFacade eatenFoodFacade;

    private String username;
    private String password;
    private User user;
    private PieChartModel caloriesPieChartModel;
    private List<EatenFood> todayEatenFoodList = new LinkedList<>();

    public void signIn() {
        user = userFacade.getUserByUsernameAndPassword(username, password);
        createPieChartModel();
        todayEatenFoodList = eatenFoodFacade.getTodayEatenFood();
        if (user != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Diet-Manager/faces/overview.xhtml");
            } catch (IOException ex) {

            }
        }
    }

    public void addEatenFood() {

    }

    private void createPieChartModel() {
        caloriesPieChartModel = new PieChartModel();
        //float allCalories = User.getDailyCalorieGoal(user);
        caloriesPieChartModel.set("Eaten Calories", 10);
        caloriesPieChartModel.set("Free Calories", 10);
        caloriesPieChartModel.setTitle("Todays Calorie Chart");
        caloriesPieChartModel.setLegendPosition("e");
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public PieChartModel getCaloriesPieChartModel() {
        if (caloriesPieChartModel == null)
            createPieChartModel();
        return caloriesPieChartModel;
    }

    public void setCaloriesPieChartModel(PieChartModel caloriesPieChartModel) {
        this.caloriesPieChartModel = caloriesPieChartModel;
    }

    public List<EatenFood> getTodayEatenFoodList() {
        return todayEatenFoodList;
    }

    public void setTodayEatenFoodList(List<EatenFood> todayEatenFoodList) {
        this.todayEatenFoodList = todayEatenFoodList;
    }
}