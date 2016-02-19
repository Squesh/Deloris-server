package com.squesh.deloris.server.core

import groovy.transform.Canonical

@Canonical
class Cell {
    int x
    int y
    boolean canPassThrough
    
    Cell(int x, int y) {
        this.x = x
        this.y = y
    }
}
