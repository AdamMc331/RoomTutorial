package com.androidessence.roomtutorial.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.androidessence.roomtutorial.entities.Account
import com.androidessence.roomtutorial.entities.Transaction

/**
 * Database access object.
 */
@Dao
interface BankDAO {
    @Query("SELECT * FROM account")
    fun getAllAccounts(): List<Account>

    @Query("SELECT * FROM transaction")
    fun getAllTransactions(): List<Transaction>

    @Insert
    fun insertAccount(vararg accounts: Account)

    @Insert
    fun insertTransaction(vararg transactions: Transaction)

    @Query("DELETE FROM account")
    fun deleteAllAccounts()
}