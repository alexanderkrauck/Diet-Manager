package at.htl.dietmanager.business;

import at.htl.dietmanager.facades.*;
import at.htl.dietmanager.model.Goal;
import at.htl.dietmanager.model.Pal;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class InitBean {

    @Inject
    GoalFacade goalFacade;

    @Inject
    PalFacade palFacade;

    @Inject
    UserFacade userFacade;

    @Inject
    FoodFacade foodFacade;

    @Inject
    EatenFoodFacade eatenFoodFacade;

    @PostConstruct
    private void init() {
        goalFacade.addGoal(new Goal(1, "Normal Diet", "The completely basic healthy diet"));
        goalFacade.addGoal(new Goal(1.2f, "Muscular Diet", "A diet for muscular bodies"));
        palFacade.addGoal(new Pal(1.2f, "Bit of sport", "A bit of that sport thing"));

    }
}
