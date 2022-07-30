package me.giverplay.grape.sdk.application;

import java.util.List;

public interface ApplicationManager {

  Application getApplication(String name);

  List<Application> getApplications();

  void launchApplication(String name);

}
