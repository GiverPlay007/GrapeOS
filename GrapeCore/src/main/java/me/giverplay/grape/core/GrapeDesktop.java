package me.giverplay.grape.core;

import me.giverplay.grape.sdk.Desktop;
import me.giverplay.grape.sdk.Taskbar;

import java.awt.Color;
import java.awt.Graphics;
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
  }

  public void draw(Graphics graphics) {
    Graphics windowGraphics = windowLayer.getGraphics();
    Graphics taskbarGraphics = taskbarLayer.getGraphics();

    graphics.setColor(Color.CYAN);
    graphics.fillRect(0, 0, width, height);

    taskbarGraphics.setColor(Color.CYAN);
    taskbarGraphics.fillRect(0, 0, width, taskbarHeight);
    taskbar.draw(taskbarGraphics);

    graphics.drawImage(windowLayer, 0, 0, width, height - taskbarHeight, null);
    graphics.drawImage(taskbarLayer, 0, height - taskbarHeight, width, taskbarHeight, null);

    windowGraphics.dispose();
    taskbarGraphics.dispose();
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
