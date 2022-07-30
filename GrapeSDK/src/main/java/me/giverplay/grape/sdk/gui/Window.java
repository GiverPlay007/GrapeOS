package me.giverplay.grape.sdk.gui;

import java.awt.Graphics;
import java.util.function.Consumer;

public interface Window {

  String getTitle();

  void setTitle(String title);

  int getWidth();

  int getHeight();

  int getX();

  int getY();

  void setPosition(int x, int y);

  void resize(int width, int height);

  void onDraw(Consumer<Graphics> onDrawHandler);

}
