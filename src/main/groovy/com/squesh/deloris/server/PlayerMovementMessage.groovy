package com.squesh.deloris.server

import groovy.transform.Canonical

@Canonical
class PlayerMovementMessage {
    int id
    int newX
    int newY
}
