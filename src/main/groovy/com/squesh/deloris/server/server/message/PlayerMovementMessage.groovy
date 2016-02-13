package com.squesh.deloris.server.server.message

import groovy.transform.Canonical

@Canonical
class PlayerMovementMessage {
    String token
    int newX
    int newY
}
