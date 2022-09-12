package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;
import com.comp301.a09nonograms.model.Model;

import java.util.Random;

public class ControllerImpl implements Controller {
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public Clues getClues() {
    int[][] rowClues = new int[model.getHeight()][model.getRowCluesLength()];
    int[][] colClues = new int[model.getWidth()][model.getColCluesLength()];
    for (int i = 0; i < model.getHeight(); i++) {
      rowClues[i] = model.getRowClues(i);
    }
    for (int i = 0; i < model.getWidth(); i++) {
      colClues[i] = model.getColClues(i);
    }
    return new CluesImpl(rowClues, colClues);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    if (model.getPuzzleIndex() < model.getPuzzleCount() - 1) {
      model.setPuzzleIndex(model.getPuzzleIndex() + 1);
    }
  }

  @Override
  public void prevPuzzle() {
    if (model.getPuzzleIndex() > 0) {
      model.setPuzzleIndex(model.getPuzzleIndex() - 1);
    }
  }

  @Override
  public void randPuzzle() {
    Random random = new Random();
    int index = random.nextInt(model.getPuzzleCount());
    model.setPuzzleIndex(index);
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
