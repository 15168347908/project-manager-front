package priv.jzy.front.animation;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class Loading {
    @AllArgsConstructor
    @Data
    public static class RunningNode {
        Node node;
        List<Transition> animation;
    }

    public static RunningNode test() {
        MoveTo startPoint = new MoveTo(0, 0);
        ArcTo arc = new ArcTo(50, 50, 0, 50, 50, false, true);
        ArcTo slowArc = new ArcTo(48, 50, 0, 50, 50, false, true);

        // 慢速弧度
        Path slowPath = new Path();
        slowPath.setStroke(Paint.valueOf("#ecf5ff"));
        slowPath.setStrokeWidth(10);
        slowPath.getElements().addAll(startPoint);
        slowPath.getElements().addAll(arc);
        AnchorPane slowRoot = new AnchorPane();
        slowRoot.setPrefHeight(100);
        slowRoot.setPrefWidth(100);
        slowRoot.getChildren().addAll(slowPath);
        slowRoot.setCenterShape(true);
        AnchorPane.setRightAnchor(slowPath, 0.0);

        // 快速弧度
        Path fastPath = new Path();
        fastPath.setStrokeWidth(5);
        fastPath.getElements().addAll(startPoint);
        fastPath.getElements().addAll(arc);
        AnchorPane fastRoot = new AnchorPane();
        fastRoot.setPrefHeight(100);
        fastRoot.setPrefWidth(100);
        fastRoot.getChildren().addAll(fastPath);

        AnchorPane.setTopAnchor(fastPath, 0.0);
        AnchorPane.setRightAnchor(fastPath, 0.0);

        AnchorPane root = new AnchorPane();
        root.setPrefWidth(100);
        root.setPrefHeight(100);
        fastRoot.setCenterShape(true);
        root.getChildren().addAll(fastRoot, slowRoot);

//
//        Creating a rotate transition
        RotateTransition slowTransition = new RotateTransition();
        //Setting the duration for the transition
        slowTransition.setDuration(Duration.millis(1000));

        //Setting the node for the transition
        slowTransition.setNode(slowRoot);
        //Setting the angle of the rotation
        slowTransition.setByAngle(360);
        //Setting the cycle count for the transition
        slowTransition.setCycleCount(Animation.INDEFINITE);
        //Setting auto reverse value to false
        slowTransition.setAutoReverse(false);
        //Playing the animation
        slowTransition.play();


        RotateTransition fastTransition = new RotateTransition();
        //Setting the duration for the transition
        fastTransition.setDuration(Duration.millis(1000));

        //Setting the node for the transition
        fastTransition.setNode(fastRoot);
        //Setting the angle of the rotation
        fastTransition.setByAngle(360);
        //Setting the cycle count for the transition
        fastTransition.setCycleCount(Animation.INDEFINITE);
        //Setting auto reverse value to false
        fastTransition.setAutoReverse(false);
        //Playing the animation
        fastTransition.play();

        return new RunningNode(root, Arrays.asList(slowTransition, fastTransition));
    }

}
