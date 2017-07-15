package com.androidessence.roomtutorial.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Represents a transaction for an account.
 */
@Entity(tableName = "transactionTable",
        foreignKeys = arrayOf(ForeignKey(entity = Account::class, parentColumns = arrayOf("name"), childColumns = arrayOf("account"), onDelete = CASCADE)),
        indices = arrayOf(Index(value = "account")))
class Transaction() {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
    var amount: Double = 0.0
    var withdrawal: Boolean = true
    var description: String = ""
    var account: String = ""

    constructor(amount: Double, withdrawal: Boolean, description: String, account: String): this() {
        this.amount = amount
        this.withdrawal = withdrawal
        this.description = description
        this.account = account
    }

    override fun equals(other: Any?): Boolean {
        return (other is Transaction
                && other.id == this.id
                && other.amount == this.amount
                && other.withdrawal == this.withdrawal
                && other.description == this.description
                && other.account == this.account)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + withdrawal.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + account.hashCode()
        return result
    }
}