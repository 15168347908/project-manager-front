package priv.jzy.front.customListener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class StyleChangeListener implements ChangeListener<Boolean> {
    private final String classStyle;
    private final Node node;

    private StyleChangeListener(String classStyle, Node bean) {
        this.classStyle = classStyle;
        this.node = bean;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        System.out.println("值改变啦, 新的值为" + newValue);
        ObservableList<String> styleClass = node.getStyleClass();
        if (newValue) styleClass.add(classStyle);
        else styleClass.removeIf(classStyle::equals);
    }

    public static ChangeListener<Boolean> build(String classStyle, Node bean) {
        return new StyleChangeListener(classStyle, bean);
    }
}
