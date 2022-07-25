package me.giverplay.grape.sdk.utils;

public final class ThreadUtils {
  private ThreadUtils() {
    throw new UnsupportedOperationException("Cannot instantiate utility class!");
  }

  public static void join(Thread thread) {
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
