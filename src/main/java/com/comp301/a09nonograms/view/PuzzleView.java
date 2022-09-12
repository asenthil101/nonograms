package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.model.Clues;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class PuzzleView implements FXComponent {
  private Controller controller;

  public PuzzleView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    Clues clues = controller.getClues();
    grid.setGridLinesVisible(true);

    for (int i = 0; i < clues.getWidth() + clues.getRowCluesLength(); i++) {
      grid.getColumnConstraints().add(new ColumnConstraints(20));
    }
    for (int i = 0; i < clues.getHeight() + clues.getColCluesLength(); i++) {
      grid.getRowConstraints().add(new RowConstraints(20));
    }

    for (int i = 0; i < clues.getWidth(); i++) {
      for (int x = 0; x < clues.getColCluesLength(); x++) {
        if (clues.getColClues(i)[x] == 0) {
          Text clue = new Text(" ");
          grid.add(clue, i + clues.getRowCluesLength(), x);
        } else {
          Text clue = new Text(Integer.toString(clues.getColClues(i)[x]));
          grid.add(clue, i + clues.getRowCluesLength(), x);
        }
      }
    }
    for (int i = 0; i < clues.getHeight(); i++) {
      for (int x = 0; x < clues.getRowCluesLength(); x++) {
        if (clues.getRowClues(i)[x] == 0) {
          Text clue = new Text(" ");
          grid.add(clue, x, i + clues.getColCluesLength());
        } else {
          Text clue = new Text(Integer.toString(clues.getRowClues(i)[x]));
          grid.add(clue, x, i + clues.getColCluesLength());
        }
      }
    }

    for (int i = clues.getRowCluesLength(); i < clues.getWidth() + clues.getRowCluesLength(); i++) {
      for (int x = clues.getColCluesLength();
          x < clues.getHeight() + clues.getColCluesLength();
          x++) {
        Button button = new Button();
        grid.add(button, i, x);
        int col = i - clues.getRowCluesLength();
        int row = x - clues.getColCluesLength();
        button.setOnMouseClicked(
            (MouseEvent click) -> {
              if (click.getButton() == MouseButton.PRIMARY) {
                controller.toggleShaded(row, col);
              } else if (click.getButton() == MouseButton.SECONDARY) {
                controller.toggleEliminated(row, col);
              }
            });
        if (controller.isShaded(row, col) == true) {
          button.setStyle("-fx-background-color: #000000;");
        }
        if (controller.isEliminated(row, col) == true) {
          button.setText("x");
          button.setAlignment(Pos.BASELINE_CENTER);
        }
      }
    }
    return grid;
  }
}
