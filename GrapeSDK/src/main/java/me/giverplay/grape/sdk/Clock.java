package me.giverplay.grape.sdk;

public interface Clock {

  boolean isRunning();

  long getTotalTicks();

  int getRefreshRate();

  int getFrameRate();

}
