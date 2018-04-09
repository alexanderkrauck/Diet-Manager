package at.htl.dietmanager.web;

import at.htl.dietmanager.facades.EatenFoodFacade;
import at.htl.dietmanager.facades.FoodFacade;
import at.htl.dietmanager.facades.GoalFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.EatenFood;
import at.htl.dietmanager.model.Food;
import at.htl.dietmanager.model.User;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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

    private List<Food> foodList;

    private EatenFood newEatenFood = new EatenFood();

    private int newSpecifiedFoodId;

    @PostConstruct
    private void postConstruct() {
        foodList = foodFacade.getAllFoods();
    }

    public void signIn() {
        user = userFacade.getUserByUsernameAndPassword(username, password);
        createPieChartModel();
        todayEatenFoodList = user.getTodayEatenFoodList();
        if (user != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Diet-Manager/faces/overview.xhtml");
            } catch (IOException ex) {

            }
        }
    }

    public void signOut() {
        user = null;
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ex) {

        }
    }

    public void addEatenFood() {
        for (Food f : foodList)
            if (f.getId() == newSpecifiedFoodId)
                newEatenFood.setFood(f);
        newEatenFood.setUser(user);
        todayEatenFoodList.add(newEatenFood);
        userFacade.update(user);
    }

    private void createPieChartModel() {
        caloriesPieChartModel = new PieChartModel();
        float allCalories = user.getDailyCalorieGoal();
        float eatenCalories = user.getTodayEatenCalories();
        caloriesPieChartModel.set("Eaten Calories", eatenCalories);
        caloriesPieChartModel.set("Free Calories", allCalories - eatenCalories);
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

    public User getUser() {
        return user;
    }

    public EatenFood getNewEatenFood() {
        return newEatenFood;
    }

    public void setNewEatenFood(EatenFood newEatenFood) {
        this.newEatenFood = newEatenFood;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public int getNewSpecifiedFoodId() {
        return newSpecifiedFoodId;
    }

    public void setNewSpecifiedFoodId(int newSpecifiedFoodId) {
        this.newSpecifiedFoodId = newSpecifiedFoodId;
    }
}