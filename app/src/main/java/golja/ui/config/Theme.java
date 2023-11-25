package golja.ui.config;

import javafx.scene.paint.Color;

public class Theme
{

  private CellTheme cellTheme;

  public Theme() { }

  static public Theme defaultTheme()
  {
    Theme theme = new Theme();

    theme.setCellTheme(CellTheme.defaultTheme());

    return theme;
  }

  public CellTheme getCellTheme() { return cellTheme; }
  public void setCellTheme(CellTheme theme) {
    this.cellTheme = theme;
  }

}

class CellTheme
{
  private Color aliveCellColor;
  private Color deadCellColor;

  public CellTheme()
  {
  }

  static public CellTheme defaultTheme()
  {
    CellTheme theme = new CellTheme();

    theme.aliveCellColor = Color.AZURE;
    theme.deadCellColor = Color.DARKGRAY;

    return theme;
  }
}
