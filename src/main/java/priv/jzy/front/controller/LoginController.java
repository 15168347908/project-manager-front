package priv.jzy.front.controller;

import com.alibaba.fastjson.JSONObject;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import priv.jzy.front.api.UserApi;
import priv.jzy.front.entity.User;
import priv.jzy.front.util.func.Try;

import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
public class LoginController implements Initializable {
    private final UserApi userApi;

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
    public void register() {
        String accountText = account.getText();
        String passwordText = password.getText();

        User user = new User();
        user.setAccount(accountText);
        user.setPassword(passwordText);

        Try<JSONObject> res = userApi.register(user);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
    }
}
