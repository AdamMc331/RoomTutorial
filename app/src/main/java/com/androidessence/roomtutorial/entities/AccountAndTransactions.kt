package com.androidessence.roomtutorial.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * Returns an account with a list of its transactions.
 */
class AccountAndTransactions {
    @Embedded
    var account: Account = Account()

    @Relation(parentColumn = "name", entityColumn = "account", entity = Transaction::class)
    var transactions: List<Transaction> = ArrayList()
}