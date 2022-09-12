package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();

    ControlView controlView = new ControlView(controller);
    layout.getChildren().add(controlView.render());

    PuzzleView puzzleView = new PuzzleView(controller);
    layout.getChildren().add(puzzleView.render());

    SolvedView solvedView = new SolvedView(controller);
    layout.getChildren().add(solvedView.render());

    return layout;
  }
}
