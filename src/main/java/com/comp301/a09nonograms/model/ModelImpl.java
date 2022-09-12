package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private List<Clues> clues;
  private List<ModelObserver> observers;
  private Clues active;
  private int[][] states;
  private int index = 0;

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new IllegalArgumentException();
    }
    this.clues = clues;
    this.observers = new ArrayList<ModelObserver>();
    this.active = clues.get(index);
    this.states = new int[active.getWidth()][active.getHeight()];
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (states[col][row] == 1) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (states[col][row] == 2) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (states[col][row] == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (states[col][row] == 0) {
      states[col][row] = 1;
    } else if (states[col][row] == 1) {
      states[col][row] = 0;
    } else if (states[col][row] == 2) {
      states[col][row] = 1;
    }
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (states[col][row] == 0) {
      states[col][row] = 2;
    } else if (states[col][row] == 2) {
      states[col][row] = 0;
    } else if (states[col][row] == 1) {
      states[col][row] = 2;
    }
    notifyObservers();
  }

  @Override
  public void clear() {
    for (int col = 0; col < states.length; col++) {
      for (int row = 0; row < states[col].length; row++) {
        states[col][row] = 0;
      }
    }
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return active.getWidth();
  }

  @Override
  public int getHeight() {
    return active.getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return active.getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return active.getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return active.getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return active.getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return clues.size();
  }

  @Override
  public int getPuzzleIndex() {
    return index;
  }

  @Override
  public void setPuzzleIndex(int index) {
    this.index = index;
    active = clues.get(index);
    states = new int[active.getWidth()][active.getHeight()];
    for (int col = 0; col < states.length; col++) {
      for (int row = 0; row < states[col].length; row++) {
        states[col][row] = 0;
      }
    }
    notifyObservers();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < getWidth(); i++) {
      int[] colClues = getColClues(i);
      int sum = 0;
      for (int x = 0; x < colClues.length; x++) {
        sum = colClues[x] + sum;
      }
      int sum2 = 0;
      for (int y = 0; y < getHeight(); y++) {
        if (isShaded(y, i) == true) {
          sum2 = sum2 + 1;
        }
      }
      if (sum != sum2) {
        return false;
      }
    }
    for (int i = 0; i < getHeight(); i++) {
      int[] rowClues = getRowClues(i);
      int sum = 0;
      for (int x = 0; x < rowClues.length; x++) {
        sum = rowClues[x] + sum;
      }
      int sum2 = 0;
      for (int y = 0; y < getWidth(); y++) {
        if (isShaded(i, y) == true) {
          sum2 = sum2 + 1;
        }
      }
      if (sum != sum2) {
        return false;
      }
    }
    return true;
  }

  private void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
