package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.skin.TextInputControlSkin;
import javafx.stage.Stage;

import java.util.List;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    List<Clues> clues = PuzzleLibrary.create();
    Model model = new ModelImpl(clues);
    Controller controller = new ControllerImpl(model);
    View view = new View(controller);

    Scene scene = new Scene(view.render());
    stage.setScene(scene);

    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });

    stage.setTitle("Nonograms");
    stage.show();
  }
}
