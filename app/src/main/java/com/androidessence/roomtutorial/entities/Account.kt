package com.androidessence.roomtutorial.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a bank account for the user.
 */
@Entity
class Account() {
    @PrimaryKey var name: String = ""

    constructor(name: String): this() {
        this.name = name
    }

    override fun equals(other: Any?): Boolean {
        return (other is Account
                && other.name == this.name)
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}