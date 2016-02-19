package com.squesh.deloris.server.core

import groovy.transform.Canonical

@Canonical
class Map {

    List<Cell> cells = []

    Map(int sizeWidth, int sizeHeight) {
        for (int i = 0; i < sizeWidth; i++) {
            for (int j = 0; j < sizeHeight; j++) {
                def cell = new Cell(i, j)
                cells << cell
            }
        }
    }

    Cell getCellAt(int x, int y) {
        def cell = cells.find({ it.x == x && it.y == y})
        if (cell == null) {
            throw new IllegalArgumentException("There is no cell at x:" + x + " and y:" + y)
        }
    }
}
