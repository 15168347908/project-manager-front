package priv.jzy.front.component;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.Objects;

public class Loading {
    private final AnchorPane loading;
    private final Pane pane;

    @SneakyThrows
    private Loading(Pane pane) {
        Objects.requireNonNull(pane, "绑定的区域不能为空！");

        URL mantleUrl = getClass().getResource("/component/mantle.fxml");
        this.loading = FXMLLoader.load(mantleUrl);
        this.pane = pane;
    }

    public static Loading bind(Pane pane) {
        return new Loading(pane);
    }

    public void show() {
        if (pane == null)
            return;

        pane.getChildren().add(loading);
    }

    public void hide() {
        pane.getChildren().remove(loading);
    }
}
