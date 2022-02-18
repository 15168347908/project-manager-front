package priv.jzy.front;

import priv.jzy.front.launch.CustomSplashScreen;
import priv.jzy.front.launch.IndexFxmlView;
import priv.jzy.front.launch.ProjectManagerApplication;


public class Main {
    public static void main(String[] args) {
        ProjectManagerApplication.launch(ProjectManagerApplication.class, IndexFxmlView.class, new CustomSplashScreen(), args);
    }
}
