package at.htl.dietmanager.business;

import at.htl.dietmanager.facades.EatenFoodFacade;
import at.htl.dietmanager.facades.FoodFacade;
import at.htl.dietmanager.facades.TargetFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.Target;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class InitBean {

    @Inject
    TargetFacade targetFacade;

    @Inject
    UserFacade userFacade;

    @Inject
    FoodFacade foodFacade;

    @Inject
    EatenFoodFacade eatenFoodFacade;

    @PostConstruct
    private void init() {
        targetFacade.addTarget(new Target(1, "Normal Diet", "The completely basic healthy diet"));
        targetFacade.addTarget(new Target(1.2f, "Muscular Diet", "A diet for muscular bodies"));
    }
}
