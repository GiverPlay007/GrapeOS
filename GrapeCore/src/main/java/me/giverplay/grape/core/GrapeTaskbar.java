package me.giverplay.grape.core;

import me.giverplay.grape.core.gui.GrapeWidget;
import me.giverplay.grape.sdk.Taskbar;
import me.giverplay.grape.sdk.gui.Widget;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GrapeTaskbar implements Taskbar {

  private final List<Widget> widgets;

  private final int width;
  private final int height;

  private final int widgetSize;
  private final int widgetOrigin;

  public GrapeTaskbar(int width, int height) {
    this.width = width;
    this.height = height;
    this.widgets = new ArrayList<>();

    widgetSize = (int) (height * 0.8);
    widgetOrigin = (height - widgetSize) / 2;
  }

  public void draw(Graphics graphics) {
    graphics.setColor(new Color(0xDA000000, true));
    graphics.fillRect(0, 0, width, height);
    graphics.setColor(Color.BLACK);

    for (int i = 0; i < widgets.size(); i++) {
      Widget widget = widgets.get(i);
      graphics.drawRect(i * height, 0, height, height);
      graphics.drawImage(widget.getIcon(), i * height + widgetOrigin, widgetOrigin, widgetSize, widgetSize, null);
    }
  }

  public void onMouseClick(int x, int y, int button) {
    int index = x / height;

    if(index >= widgets.size()) return;

    GrapeWidget widget = (GrapeWidget) widgets.get(index);
    widget.getCallback().run();
  }

  @Override
  public List<Widget> getWidgets() {
    return new ArrayList<>(widgets);
  }

  @Override
  public void addWidget(Widget widget) {
    widgets.add(widget);
  }

  @Override
  public void removeWidget(Widget widget) {
    widgets.remove(widget);
  }

  @Override
  public Widget getWidget(String title) {
    for(Widget widget : widgets) {
      if(widget.getTitle().equals(title)) {
        return widget;
      }
    }

    return null;
  }
}
