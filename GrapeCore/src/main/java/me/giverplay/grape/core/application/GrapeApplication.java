package me.giverplay.grape.core.application;

import me.giverplay.grape.sdk.application.Application;

public class GrapeApplication implements Application {

  private final String name;

  public GrapeApplication(String name) {
    this.name = name;
  }

  @Override
  public void onCreate() {

  }

  @Override
  public void onDestroy() {

  }

  @Override
  public String getName() {
    return name;
  }
}
