package com.example.blockgraming19

import java.io.Serializable

class Cricketer : Serializable {
    var cricketerName: String? = null
    var teamName: String? = null

    constructor() {}
    constructor(cricketerName: String?, teamName: String?) {
        this.cricketerName = cricketerName
        this.teamName = teamName
    }
}