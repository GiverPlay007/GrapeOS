package me.giverplay.grape.core;

import me.giverplay.grape.core.application.GrapeApplication;
import me.giverplay.grape.core.gui.GrapeWidget;
import me.giverplay.grape.core.gui.GrapeWindow;
import me.giverplay.grape.sdk.Desktop;
import me.giverplay.grape.sdk.Taskbar;
import me.giverplay.grape.sdk.application.Application;
import me.giverplay.grape.sdk.gui.Widget;
import me.giverplay.grape.sdk.gui.Window;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GrapeDesktop implements Desktop {
  private final GrapeOS grape;
  private final GrapeTaskbar taskbar;

  private final BufferedImage taskbarLayer;
  private final BufferedImage desktopLayer;

  private final List<GrapeWindow> windows;

  private final int width;
  private final int height;
  private final int taskbarHeight;

  public GrapeDesktop(GrapeOS grape, int width, int height, int taskbarHeight) {
    this.grape = grape;
    this.taskbar = new GrapeTaskbar(width, taskbarHeight);
    this.width = width;
    this.height = height;
    this.taskbarHeight = taskbarHeight;

    this.desktopLayer = new BufferedImage(width, height - taskbarHeight, BufferedImage.TYPE_INT_ARGB);
    this.taskbarLayer = new BufferedImage(width, taskbarHeight, BufferedImage.TYPE_INT_ARGB);

    this.windows = new ArrayList<>();

    Image shutdownImage = grape.getResourceManager().getImage("/shutdown.png");
    Widget shutdownWidget = new GrapeWidget("Shutdown", shutdownImage, 0, 0, () -> grape.shutdown("Good bye!"));

    taskbar.addWidget(shutdownWidget);
  }

  public void draw(Graphics graphics) {
    Graphics desktopGraphics = desktopLayer.getGraphics();
    Graphics taskbarGraphics = taskbarLayer.getGraphics();

    for (GrapeWindow window : windows) {
      BufferedImage windowLayer = window.getLayer();

      if(windowLayer != null) {
        Graphics windowGraphics = windowLayer.getGraphics();
        window.draw(windowGraphics);
        windowGraphics.dispose();
      }

      int width = window.getWidth();
      int height = window.getHeight();
      int x = window.getX();
      int y = window.getY();

      desktopGraphics.setColor(Color.black);
      desktopGraphics.drawRect(x, y, width + 1, height + 1);
      desktopGraphics.fillRect(x, y - 26, width + 2, 26);
      desktopGraphics.setColor(Color.WHITE);
      desktopGraphics.setFont(new Font("Arial", Font.PLAIN, 16));

      FontMetrics metrics = desktopGraphics.getFontMetrics();
      desktopGraphics.drawString(window.getTitle(), x + (width - metrics.stringWidth(window.getTitle())) / 2, y - 6);
      desktopGraphics.drawImage(windowLayer, x + 1, y + 1, width, height, null);
    }

    graphics.setColor(Color.LIGHT_GRAY);
    graphics.fillRect(0, 0, width, height);

    taskbarGraphics.setColor(Color.LIGHT_GRAY);
    taskbarGraphics.fillRect(0, 0, width, taskbarHeight);
    taskbar.draw(taskbarGraphics);

    graphics.drawImage(desktopLayer, 0, 0, width, height - taskbarHeight, null);
    graphics.drawImage(taskbarLayer, 0, height - taskbarHeight, width, taskbarHeight, null);

    desktopGraphics.dispose();
    taskbarGraphics.dispose();
  }

  public void onMouseClick(int x, int y, int button) {
    if(y < height - taskbarHeight) {
      return;
    }

    taskbar.onMouseClick(x, height - y, button);
  }

  @Override
  public Window createWindow(Application context, String title, int width, int height) {
    GrapeWindow window = new GrapeWindow((GrapeApplication) context, title, width, height);
    windows.add(window);
    return window;
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
