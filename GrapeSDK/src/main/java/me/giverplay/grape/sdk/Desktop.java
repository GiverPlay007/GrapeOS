package me.giverplay.grape.sdk;

import me.giverplay.grape.sdk.application.Application;
import me.giverplay.grape.sdk.gui.Window;

public interface Desktop {

  int getWidth();

  int getHeight();

  Taskbar getTaskbar();

  Window createWindow(Application context, String title, int width, int height);

}
