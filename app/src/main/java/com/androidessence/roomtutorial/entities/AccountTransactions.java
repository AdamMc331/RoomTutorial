package com.androidessence.roomtutorial.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Manages an account and the transactions for it.
 */
public class AccountTransactions {
    @Embedded
    public Account account;

    @Relation(parentColumn = "name", entityColumn = "account")
    public List<Transaction> transactions;
}
