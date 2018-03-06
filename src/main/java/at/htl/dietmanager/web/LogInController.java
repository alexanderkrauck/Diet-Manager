package at.htl.dietmanager.web;

import at.htl.dietmanager.facades.TargetFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;

@ManagedBean
//@SessionScoped
public class LogInController {
    @Inject
    private UserFacade userFacade;

    @Inject
    private TargetFacade targetFacade;

    private String username;
    private String password;

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

    public void login() {
        User user = userFacade.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Diet-Manager/faces/overview.xhtml");
            } catch (IOException ex) {

            }
        }
    }
}
