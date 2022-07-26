package me.giverplay.grape.core;

import me.giverplay.grape.sdk.Clock;
import me.giverplay.grape.sdk.utils.ThreadUtils;

public final class GrapeClock implements Clock {
  private static final double NS_PER_TICK = 1_000_000_000 / 60.0D;

  private final GrapeOS grape;

  private Thread clockThread;

  private int refreshRate;
  private int frameRate;
  private long totalTicks;

  private volatile boolean isRunning;

  public GrapeClock(GrapeOS grape) {
    this.grape = grape;
  }

  protected void start() {
    isRunning = true;
    clockThread = new Thread(this::loop, "Clock Thread");
    clockThread.start();
  }

  protected void stop(Runnable callback) {
    System.out.println("Stoping clock...");
    isRunning = false;

    new Thread(() -> {
      ThreadUtils.join(clockThread);
      callback.run();
    }, "Join Thread").start();
  }

  private void loop() {
    long lastTime = System.nanoTime();
    long time = System.currentTimeMillis();
    long now;

    double unprocessed = 0.0D;

    int ticks = 0;
    int frames = 0;

    while (isRunning) {
      now = System.nanoTime();
      unprocessed += (now - lastTime) / NS_PER_TICK;
      lastTime = now;

      while(unprocessed >= 1) {
        grape.refresh();
        ++ticks;
        ++totalTicks;
        --unprocessed;
      }

      grape.draw();
      ++frames;

      if(System.currentTimeMillis() - time >= 1000) {
        refreshRate = ticks;
        frameRate = frames;
        ticks = 0;
        frames = 0;
        time += 1000;
        System.out.println("TPS: " + refreshRate + " | FPS: " + frameRate);
      }
    }
  }

  @Override
  public boolean isRunning() {
    return isRunning;
  }

  @Override
  public long getTotalTicks() {
    return totalTicks;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getFrameRate() {
    return frameRate;
  }
}
