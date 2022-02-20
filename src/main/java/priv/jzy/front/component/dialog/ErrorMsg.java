package priv.jzy.front.component.dialog;

import javafx.scene.control.Alert;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class ErrorMsg extends Alert {
    public static final String DEFAULT_TITLE = "异常";
    public static final String DEFAULT_HEADER_TEXT = "操作失败！";

    private ErrorMsg() {
        super(ERROR);
    }

    public static ErrorMsg create(String title,
                                  String headerText,
                                  String contextText) {
        ErrorMsg errorDialog = new ErrorMsg();
        errorDialog.setTitle(title);
        errorDialog.setHeaderText(headerText);
        errorDialog.setContentText(contextText);
        return errorDialog;
    }

    public static ErrorMsg create(Exception error) {
        return create(DEFAULT_TITLE, DEFAULT_HEADER_TEXT, Optional.ofNullable(error)
                .map(Throwable::getMessage).orElse(null));
    }

    public static ErrorMsg create(String errorMessage) {
        return create(DEFAULT_TITLE, DEFAULT_HEADER_TEXT, errorMessage);
    }
}
