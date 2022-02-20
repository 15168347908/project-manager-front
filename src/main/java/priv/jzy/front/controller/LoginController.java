package priv.jzy.front.controller;

import com.alibaba.fastjson.JSONObject;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import priv.jzy.front.api.UserApi;
import priv.jzy.front.component.Loading;
import priv.jzy.front.component.dialog.ErrorMsg;
import priv.jzy.front.component.dialog.SuccessMsg;
import priv.jzy.front.entity.R;
import priv.jzy.front.entity.User;
import priv.jzy.front.enumeration.ReturnCode;
import priv.jzy.front.util.func.Try;

import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class LoginController implements Initializable {
    private final UserApi userApi;

    public FlowPane container;
    public TextArea password;
    public TextArea account;

    private ResourceBundle resourceBundle;

    public LoginController(UserApi userApi) {
        this.userApi = userApi;
    }

    @FXML
    public void login() {
    }

    @FXML
    public void register() throws Exception {
        String accountText = account.getText();
        String passwordText = password.getText();

        User user = new User();
        user.setAccount(accountText);
        user.setPassword(passwordText);

        // 蒙层
        Loading loading = Loading.bind(container);
        loading.show();

        Try<R<JSONObject>> tryRes = userApi.register(user);
        if (tryRes.isFailure()) {
            ErrorMsg.create(tryRes.toFailure().getException()).showAndWait();
            return;
        }

        R<JSONObject> res = tryRes.get();
        if (ReturnCode.SUCCESS.getCode().equals(res.getCode())) {
            SuccessMsg.create("创建用户账号成功！").show();
            // 跳转页面
        } else {
            ErrorMsg.create(res.getMsg()).show();
        }

        loading.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
    }
}
