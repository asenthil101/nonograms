package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private final Controller controller;

  public ControlView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox controls = new HBox();

    Button nextPuzzle = new Button("Next Puzzle");
    nextPuzzle.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        });
    controls.getChildren().add(nextPuzzle);

    Button previousPuzzle = new Button("Previous Puzzle");
    previousPuzzle.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
        });
    controls.getChildren().add(previousPuzzle);

    Button randomPuzzle = new Button("Random Puzzle");
    randomPuzzle.setOnAction(
        (ActionEvent event) -> {
          controller.randPuzzle();
        });
    controls.getChildren().add(randomPuzzle);

    Button clear = new Button("Clear");
    clear.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
        });
    controls.getChildren().add(clear);

    return controls;
  }
}
