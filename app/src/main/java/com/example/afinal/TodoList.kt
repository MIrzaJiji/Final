package com.example.afinal

class TodoList {

    var id: Int = 0

    var title: String = ""

    var description: String = ""

    constructor(title: String, description: String) {
        this.title = title
        this.description = description
    }
    constructor() {

    }
}