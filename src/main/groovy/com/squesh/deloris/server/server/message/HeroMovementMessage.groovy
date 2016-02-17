package com.squesh.deloris.server.server.message

import groovy.transform.Canonical

@Canonical
class HeroMovementMessage {
    String token
    int newX
    int newY
}
