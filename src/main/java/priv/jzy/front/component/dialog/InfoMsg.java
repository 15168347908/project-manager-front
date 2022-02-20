package priv.jzy.front.component.dialog;

import javafx.scene.control.Alert;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class InfoMsg extends Alert {
    public static final String DEFAULT_TITLE = "消息";

    private InfoMsg() {
        super(INFORMATION);
    }

    public static InfoMsg create(String contextText) {
        InfoMsg messageDialog = new InfoMsg();
        messageDialog.setTitle(DEFAULT_TITLE);
        messageDialog.setContentText(contextText);
        return messageDialog;
    }
}
