package priv.jzy.front.component;

import priv.jzy.front.util.SvgUtil;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

import java.util.Optional;

public class Icon extends Region {
    private StringProperty iconPath;
    private StringProperty iconColor;
    private SVGPath svgPath;

    private static final double DEFAULT_WIDTH = 30.0;
    private static final double DEFAULT_HEIGHT = 30.0;
    private static final String DEFAULT_ICON = "cangpeitubiao_tongzhi.svg";
    private static final String DEFAULT_COLOR = "#606266";

    public Icon(@NamedArg("width") Double width, @NamedArg("height") Double height) {
        System.out.println("第一个构造器");
        setSide(Optional.ofNullable(width).orElse(DEFAULT_WIDTH),
                Optional.ofNullable(height).orElse(DEFAULT_HEIGHT));
        setIconPath(DEFAULT_ICON);
        setIconColor(DEFAULT_COLOR);
        setIcon(DEFAULT_ICON, DEFAULT_COLOR);
        initListener();
    }

    public Icon(@NamedArg("iconPath") String iconPath, @NamedArg("iconColor") String iconColor) {
        System.out.println("第二个构造器");
        setSide(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setIconPath(iconPath);
        setIconColor(iconColor);
        setIcon(iconPath, iconColor);
        initListener();
    }

    public Icon(@NamedArg("width") Double width, @NamedArg("height") Double height,
                @NamedArg("iconPath") String iconPath, @NamedArg("iconColor") String iconColor) {
        System.out.println("第三个构造器");
        setSide(Optional.ofNullable(width).orElse(DEFAULT_WIDTH),
                Optional.ofNullable(height).orElse(DEFAULT_HEIGHT));
        setIconPath(iconPath);
        setIconColor(iconColor);
        setIcon(iconPath, iconColor);
        initListener();
    }

    private void setSide(double width, double height) {
        setWidth(width);
        setHeight(height);
        setPrefSize(width, height);
        setMinSize(width, height);
        setMaxSize(width, height);
    }

    public SVGPath getSvgPath() {
        return svgPath;
    }

    private void initListener() {
        iconPath.addListener((observable, oldVal, newVal) -> setIcon(newVal, iconColor.getValueSafe()));
        iconColor.addListener((observable, oldVal, newVal) -> svgPath.setFill(Paint.valueOf(newVal)));
    }

    private void setIcon(String iconPath, String iconColor) {
        svgPath = SvgUtil.getSVGPathByName(iconPath, iconColor);
        setShape(svgPath);
    }

    public void setIconPath(String iconPath) {
        if (this.iconPath == null) {
            this.iconPath = new SimpleStringProperty(this, "iconPath", iconPath);
        }
        this.iconPath.set(iconPath);
    }

    public String getIconPath() {
        return this.iconPath.get();
    }

    public void setIconColor(String iconColor) {
        if (this.iconColor == null) {
            this.iconColor = new SimpleStringProperty(this, "iconColor", iconColor);
        }
        this.iconColor.set(iconColor);
    }

    public String getIconColor() {
        return iconColor.getValueSafe();
    }

}
