package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.awt.*;

public class SolvedView implements FXComponent {
  public final Controller controller;

  public SolvedView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox solved = new HBox();

    javafx.scene.control.Label label;
    if (controller.isSolved() == false) {
      label =
          new javafx.scene.control.Label(
              "Puzzle "
                  + (controller.getPuzzleIndex() + 1)
                  + " of "
                  + (controller.getPuzzleCount()));
    } else {
      label = new Label("Puzzle has been solved!");
      label.setStyle("-fx-background-color: #00ff00;");
    }
    solved.setAlignment(Pos.CENTER);
    label.setFont(new Font("Arial", 12));
    solved.getChildren().add(label);
    return solved;
  }
}
