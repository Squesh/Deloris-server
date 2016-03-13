package server.core.map;

import java.util.ArrayList;
import java.util.List;

public class GlobalMap {
    private List<Cell> cells = new ArrayList<>();

    public GlobalMap(int sizeWidth, int sizeHeight) {
        for (int i = 0; i < sizeWidth; i++) {
            for (int j = 0; j < sizeHeight; j++) {
                Cell cell = new Cell(i, j, TerrainType.EARTH);
                cells.add(cell);
            }
        }
    }

    public Cell getCellAt(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        throw new RuntimeException("dafuq");
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
