package priv.jzy.front.component.dialog;

import javafx.scene.control.Alert;

import static javafx.scene.control.Alert.AlertType.WARNING;

public class WarningMsg extends Alert {
    public static final String DEFAULT_TITLE = "警告";

    private WarningMsg() {
        super(WARNING);
    }

    public static WarningMsg create(String contextText) {
        WarningMsg warningMsgDialog = new WarningMsg();
        warningMsgDialog.setTitle(DEFAULT_TITLE);
        warningMsgDialog.setContentText(contextText);
        return warningMsgDialog;
    }
}
