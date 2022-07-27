package me.giverplay.grape.core;

import me.giverplay.grape.core.gui.GrapeWidget;
import me.giverplay.grape.sdk.Desktop;
import me.giverplay.grape.sdk.Taskbar;
import me.giverplay.grape.sdk.gui.Widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class GrapeDesktop implements Desktop {
  private final GrapeOS grape;
  private final GrapeTaskbar taskbar;

  private final BufferedImage taskbarLayer;
  private final BufferedImage windowLayer;

  private final int width;
  private final int height;
  private final int taskbarHeight;

  public GrapeDesktop(GrapeOS grape, int width, int height, int taskbarHeight) {
    this.grape = grape;
    this.taskbar = new GrapeTaskbar(width, taskbarHeight);
    this.width = width;
    this.height = height;
    this.taskbarHeight = taskbarHeight;

    this.windowLayer = new BufferedImage(width, height - taskbarHeight, BufferedImage.TYPE_INT_ARGB);
    this.taskbarLayer = new BufferedImage(width, taskbarHeight, BufferedImage.TYPE_INT_ARGB);

    Image shutdownImage = grape.getResourceManager().getImage("/shutdown.png");
    Widget shutdownWidget = new GrapeWidget("Shutdown", shutdownImage, 0, 0, () -> grape.shutdown("Good bye!"));

    taskbar.addWidget(shutdownWidget);
  }

  public void draw(Graphics graphics) {
    Graphics windowGraphics = windowLayer.getGraphics();
    Graphics taskbarGraphics = taskbarLayer.getGraphics();

    graphics.setColor(Color.LIGHT_GRAY);
    graphics.fillRect(0, 0, width, height);

    taskbarGraphics.setColor(Color.LIGHT_GRAY);
    taskbarGraphics.fillRect(0, 0, width, taskbarHeight);
    taskbar.draw(taskbarGraphics);

    graphics.drawImage(windowLayer, 0, 0, width, height - taskbarHeight, null);
    graphics.drawImage(taskbarLayer, 0, height - taskbarHeight, width, taskbarHeight, null);

    windowGraphics.dispose();
    taskbarGraphics.dispose();
  }

  public void onMouseClick(int x, int y, int button) {
    if(y < height - taskbarHeight) {
      return;
    }

    taskbar.onMouseClick(x, height - y, button);
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Taskbar getTaskbar() {
    return taskbar;
  }
}
