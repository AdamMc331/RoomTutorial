package com.androidessence.roomtutorial.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a transaction for an account.
 */
@Entity(foreignKeys = arrayOf(ForeignKey(entity = Account::class, parentColumns = arrayOf("name"), childColumns = arrayOf("account"))))
class Transaction() {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var amount: Double = 0.0
    var withdrawal: Boolean = true
    var description: String = ""
    var account: String = ""

    constructor(id: Int, amount: Double, withdrawal: Boolean, description: String, account: String): this() {
        this.id = id
        this.amount = amount
        this.withdrawal = withdrawal
        this.description = description
        this.account = account
    }
}