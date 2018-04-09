package at.htl.dietmanager.business;

import at.htl.dietmanager.facades.*;
import at.htl.dietmanager.model.Food;
import at.htl.dietmanager.model.Goal;
import at.htl.dietmanager.model.Pal;
import at.htl.dietmanager.model.User;
import at.htl.dietmanager.model.enums.Gender;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDate;

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
        Goal goal = new Goal(1, "Normal Diet", "The completely basic healthy diet");
        Pal pal = new Pal(1.2f, "Bit of sport", "A bit of that sport thing");
        goalFacade.addGoal(goal);
        goalFacade.addGoal(new Goal(1.2f, "Muscular Diet", "A diet for muscular bodies"));
        palFacade.addGoal(pal);
        userFacade.addUser(new User("alex", "krauck", "alex.krauck@gmail.com", 200, 100, Gender.MALE, goal, pal, LocalDate.now()));

        Food food = new Food("corn", "cornycorncorn", 400);
        foodFacade.addFood(food);
    }
}
