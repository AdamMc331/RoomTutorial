package com.androidessence.roomtutorial.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.androidessence.roomtutorial.entities.Account
import com.androidessence.roomtutorial.entities.Transaction

/**
 * Database class.
 */
@Database(entities = arrayOf(Account::class, Transaction::class), version = 1)
abstract class BankDatabase : RoomDatabase() {
	abstract fun bankDao(): BankDAO

	companion object {
		private var INSTANCE: BankDatabase? = null

		fun getInMemoryDatabase(context: Context): BankDatabase {
			if (INSTANCE == null) {
				INSTANCE = Room.databaseBuilder(context, BankDatabase::class.java, "bank-database")
						.build()
			}

			return INSTANCE!!
		}
	}
}