package at.htl.dietmanager.web;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OverviewToolbarController {
    private Possibility possibility = Possibility.CreateNewUnspecifiedEatenFood;

    private enum Possibility {
        CreateNewSpecifiedEatenFood, CreateNewUnspecifiedEatenFood, CreateNewFood
    }


    public void clickCreateSpecifiedEatenFood() {
        possibility = Possibility.CreateNewSpecifiedEatenFood;
    }

    public void clickCreateUnspecifiedEatenFood() {
        possibility = Possibility.CreateNewUnspecifiedEatenFood;
    }

    public void clickCreateNewFood() {
        possibility = Possibility.CreateNewFood;
    }

    public Possibility getPossibility() {
        return possibility;
    }

    public void setPossibility(Possibility possibility) {
        this.possibility = possibility;
    }
}
