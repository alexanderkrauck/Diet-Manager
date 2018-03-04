package at.htl.dietmanager.web;

import at.htl.dietmanager.facades.TargetFacade;
import at.htl.dietmanager.facades.UserFacade;
import at.htl.dietmanager.model.Target;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class IndexController {
    @Inject
    UserFacade userFacade;

    @Inject
    TargetFacade targetFacade;

    List<Target> targetList;

    public IndexController() {
    }

    @PostConstruct
    public void postConstruct() {
        if (targetList == null)
            targetList = targetFacade.getAllTargets();
    }

    public List<Target> getTargetList() {
        return targetList;
    }
}
