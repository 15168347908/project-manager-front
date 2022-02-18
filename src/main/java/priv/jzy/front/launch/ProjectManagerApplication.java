package priv.jzy.front.launch;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "priv.jzy.front")
public class ProjectManagerApplication extends AbstractJavaFxApplicationSupport {
}
