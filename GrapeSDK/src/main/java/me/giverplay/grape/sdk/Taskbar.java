package me.giverplay.grape.sdk;

import me.giverplay.grape.sdk.gui.Widget;

import java.util.List;

public interface Taskbar {

  List<Widget> getWidgets();

  void addWidget(Widget widget);

  void removeWidget(Widget widget);

  Widget getWidget(String title);

}
