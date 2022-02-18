package priv.jzy.front.launch;

import de.felixroske.jfxsupport.SplashScreen;

public class CustomSplashScreen extends SplashScreen {
    @Override
    public String getImagePath() {
        return "/images/loading.gif";
    }
}
