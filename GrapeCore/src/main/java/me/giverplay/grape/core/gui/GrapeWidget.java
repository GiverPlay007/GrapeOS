package me.giverplay.grape.core.gui;

import me.giverplay.grape.sdk.gui.Widget;

import java.awt.Image;

public class GrapeWidget implements Widget {

  private final Runnable callback;
  private final Image icon;
  private final String title;

  private final int width;
  private final int height;

  public GrapeWidget(String title, Image icon, int width, int height, Runnable callback) {
    this.title = title;
    this.icon = icon;
    this.width = width;
    this.height = height;
    this.callback = callback;
  }

  @Override
  public Image getIcon() {
    return icon;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  public Runnable getCallback() {
    return callback;
  }
}
