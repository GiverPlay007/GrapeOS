package me.giverplay.grape.sdk;

public interface Grape {

  Clock getClock();

  Desktop getDesktop();

  void shutdown(String message, int seconds);

  default void shutdown(String message) {
    shutdown(message, 0);
  }
}
