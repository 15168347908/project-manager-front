package priv.jzy.front.component.dialog;

import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

public class SuccessMsg extends Alert {
    public static final String DEFAULT_TITLE = "成功";

    private SuccessMsg() {
        super(AlertType.INFORMATION);
    }

    public static SuccessMsg create(String contextText) {
        SuccessMsg successMsgDialog = new SuccessMsg();
        successMsgDialog.setTitle(DEFAULT_TITLE);
        successMsgDialog.setContentText(contextText);

        ImageView icon = new ImageView("/icon/cangpeitubiao_wanjiecaigoudanwanjiehuanhuodan.svg");
        successMsgDialog.setGraphic(icon);
        return successMsgDialog;
    }
}
