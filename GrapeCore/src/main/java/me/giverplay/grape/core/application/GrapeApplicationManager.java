package me.giverplay.grape.core.application;

import me.giverplay.grape.core.GrapeOS;
import me.giverplay.grape.sdk.application.Application;
import me.giverplay.grape.sdk.application.ApplicationManager;

import java.util.ArrayList;
import java.util.List;

public class GrapeApplicationManager implements ApplicationManager {

  private final List<Application> applications = new ArrayList<>();
  private final GrapeOS grape;

  public GrapeApplicationManager(GrapeOS grape) {
    this.grape = grape;
  }

  @Override
  public Application getApplication(String name) {
    for (Application application : applications) {
      if(application.getName().equals(name)) {
        return application;
      }
    }

    return null;
  }

  @Override
  public List<Application> getApplications() {
    return new ArrayList<>(applications);
  }

  @Override
  public void launchApplication(String name) {
    for (Application application : applications) {
      if(application.getName().equals(name)) {
        throw new UnsupportedOperationException("Unimplemented!");
      }
    }
  }
}
