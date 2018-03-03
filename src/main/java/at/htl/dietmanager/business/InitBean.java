package at.htl.dietmanager.business;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class InitBean {

    @PostConstruct
    private void init() {

    }
}
