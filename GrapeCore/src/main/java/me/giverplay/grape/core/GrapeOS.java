package me.giverplay.grape.core;

import me.giverplay.grape.core.screen.Screen;
import me.giverplay.grape.sdk.Clock;
import me.giverplay.grape.sdk.Desktop;
import me.giverplay.grape.sdk.Grape;

public final class GrapeOS implements Grape {
  private final Screen screen;

  private final GrapeClock clock;
  private final GrapeDesktop desktop;

  public GrapeOS() {
    screen = new Screen("GrapeOS", 1280, 720);
    clock = new GrapeClock(this);
    desktop = new GrapeDesktop(this, screen.getWidth(), screen.getHeight(), 48);
  }

  public void start() {
    screen.getFrame().setVisible(true);
    clock.start();
  }

  protected void refresh() {

  }

  protected void draw() {
    screen.beginRender();
    desktop.draw(screen.getRenderGraphics());
    screen.endRender();
  }

  @Override
  public void shutdown(String message, int seconds) {
    clock.stop(this::shutdown0);
  }

  private void shutdown0() {
    System.out.println("Bye!");
    System.exit(0);
  }

  @Override
  public Clock getClock() {
    return clock;
  }

  @Override
  public Desktop getDesktop() {
    return desktop;
  }
}
