package com.androidessence.roomtutorial.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.androidessence.roomtutorial.entities.Account
import com.androidessence.roomtutorial.entities.AccountTransactions
import com.androidessence.roomtutorial.entities.Transaction

/**
 * Database access object.
 */
@Dao
interface BankDAO {
    @Query("SELECT * FROM account")
    fun getAllAccounts(): List<Account>

    @Query("SELECT * FROM transactionTable")
    fun getAllTransactions(): List<Transaction>

    @Query("SELECT * FROM transactionTable WHERE account = :arg0")
    fun getTransactionsForAccount(account: String): List<Transaction>

    @Query("SELECT * FROM account")
    fun getAccountsWithTransactions(): List<AccountTransactions>

    @Insert
    fun insertAccount(vararg accounts: Account)

    @Insert
    fun insertTransaction(vararg transactions: Transaction): List<Long>

    @Query("DELETE FROM account")
    fun deleteAllAccounts()

    @Query("DELETE FROM transactionTable")
    fun deleteAllTransactions()
}