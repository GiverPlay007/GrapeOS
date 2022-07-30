package me.giverplay.grape.core;

import me.giverplay.grape.core.application.GrapeApplication;
import me.giverplay.grape.core.application.GrapeApplicationManager;
import me.giverplay.grape.core.resource.ResourceManager;
import me.giverplay.grape.core.screen.InputHandler;
import me.giverplay.grape.core.screen.Screen;
import me.giverplay.grape.sdk.Clock;
import me.giverplay.grape.sdk.Desktop;
import me.giverplay.grape.sdk.Grape;
import me.giverplay.grape.sdk.gui.Window;

import java.awt.Image;

public final class GrapeOS implements Grape {
  private final Screen screen;

  private final GrapeClock clock;
  private final GrapeDesktop desktop;
  private final GrapeApplicationManager applicationManager;
  private final ResourceManager resourceManager;
  private final InputHandler inputHandler;

  public GrapeOS() {
    screen = new Screen("GrapeOS", 1280, 720);
    inputHandler = new InputHandler(this);
    resourceManager = new ResourceManager(this);
    clock = new GrapeClock(this);
    desktop = new GrapeDesktop(this, screen.getWidth(), screen.getHeight(), 48);
    applicationManager = new GrapeApplicationManager(this);

    Image miguel = resourceManager.getImage("/miguel.jpeg");

    GrapeApplication application = new GrapeApplication("Miguel");
    Window window = desktop.createWindow(application, "Miguel: GrapeOS Edition", 500, 320);
    window.setPosition(400, 280);
    window.onDraw(g -> g.drawImage(miguel, 0, 0, window.getWidth(), window.getHeight(), null));
  }

  public void start() {
    screen.getFrame().setVisible(true);
    screen.getCanvas().addMouseListener(inputHandler);
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
    System.out.println("Shutting down: " + message);
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

  public ResourceManager getResourceManager() {
    return resourceManager;
  }

  public GrapeApplicationManager getApplicationManager() {
    return applicationManager;
  }
}
