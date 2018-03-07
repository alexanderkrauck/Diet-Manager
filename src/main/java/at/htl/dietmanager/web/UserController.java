package at.htl.dietmanager.web;

import at.htl.dietmanager.facades.EatenFoodFacade;
import at.htl.dietmanager.facades.FoodFacade;
import at.htl.dietmanager.facades.GoalFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

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
    User user;

    public void signIn() {
        user = userFacade.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Diet-Manager/faces/overview.xhtml");

            } catch (IOException ex) {

            }
        }
    }

    public void addEatenFood() {

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
}