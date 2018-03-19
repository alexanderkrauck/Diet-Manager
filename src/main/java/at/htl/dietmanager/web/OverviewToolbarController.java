package at.htl.dietmanager.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class OverviewToolbarController {
    private Possibility possibility = Possibility.CreateNewUnspecifiedEatenFood;

    private enum Possibility {
        CreateNewSpecifiedEatenFood, CreateNewUnspecifiedEatenFood, CreateNewFood
    }


    public void clickCreateSpecifiedEatenFood() {
        possibility = Possibility.CreateNewSpecifiedEatenFood;
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ex) {

        }
    }

    public void clickCreateUnspecifiedEatenFood() {
        possibility = Possibility.CreateNewUnspecifiedEatenFood;
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ex) {

        }
    }

    public void clickCreateNewFood() {
        possibility = Possibility.CreateNewFood;
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ex) {

        }
    }

    public Possibility getPossibility() {
        return possibility;
    }

    public void setPossibility(Possibility possibility) {
        this.possibility = possibility;
    }
}
