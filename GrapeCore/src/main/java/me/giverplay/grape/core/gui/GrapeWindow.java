package me.giverplay.grape.core.gui;

import me.giverplay.grape.core.application.GrapeApplication;
import me.giverplay.grape.sdk.gui.Window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class GrapeWindow implements Window {

  private final GrapeApplication context;

  private Consumer<Graphics> onDrawHandler;
  private BufferedImage layer;
  private String title;

  private int width;
  private int height;

  private int x;
  private int y;

  public GrapeWindow(GrapeApplication context, String title, int width, int height) {
    this.context = context;
    this.title = title;
    resize(width, height);
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
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
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void resize(int width, int height) {
    this.layer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    this.width = width;
    this.height = height;
  }

  @Override
  public void onDraw(Consumer<Graphics> onDrawHandler) {
    this.onDrawHandler = onDrawHandler;
  }

  public void draw(Graphics graphics) {
    if(onDrawHandler != null) {
      onDrawHandler.accept(graphics);
    }
  }

  public BufferedImage getLayer() {
    return layer;
  }
}
