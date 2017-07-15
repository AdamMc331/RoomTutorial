package com.androidessence.roomtutorial

import android.arch.persistence.room.Room
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.androidessence.roomtutorial.data.BankDAO
import com.androidessence.roomtutorial.data.BankDatabase
import com.androidessence.roomtutorial.entities.Account
import com.androidessence.roomtutorial.entities.Transaction
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Tests our implementation of Room.
 */
@RunWith(AndroidJUnit4::class)
class RoomTesting {
    @Rule @JvmField val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var bankDao: BankDAO
    private lateinit var bankDatabase: BankDatabase

    @Before
    fun createDb() {
        //bankDatabase = BankDatabase.getInMemoryDatabase(activityRule.activity)
        bankDatabase = Room.inMemoryDatabaseBuilder(activityRule.activity, BankDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        bankDao = bankDatabase.bankDao()

        bankDao.deleteAllAccounts()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        bankDatabase.close()
    }

    @Test
    fun writeReadAccount() {
        val testAccount = Account("Checking")
        bankDao.insertAccount(testAccount)

        val accounts = bankDao.getAllAccounts()
        assertEquals(accounts[0], testAccount)
    }

    @Test
    fun writeReadTransaction() {
        val testAccount = Account("Checking")
        bankDao.insertAccount(testAccount)

        val testTransaction = Transaction(5.00, true, "Lunch", "Checking")
        val id = bankDao.insertTransaction(testTransaction).first()
        testTransaction.id = id

        val transactions = bankDao.getAllTransactions()
        assertEquals(transactions[0], testTransaction)
    }

    @Test
    fun writeReadTransactionForAccount() {
        val testAccount = Account("Checking")
        bankDao.insertAccount(testAccount)

        val testTransaction = Transaction(5.00, true, "Lunch", "Checking")
        val id = bankDao.insertTransaction(testTransaction).first()
        testTransaction.id = id

        val transactions = bankDao.getTransactionsForAccount("Checking")
        assertEquals(transactions[0], testTransaction)
    }
}

//@RunWith(AndroidJUnit4.class)
//public class SimpleEntityReadWriteTest {
//    private UserDao mUserDao;
//    private TestDatabase mDb;
//
//    @Before
//    public void createDb() {
//        Context context = InstrumentationRegistry.getTargetContext();
//        mDb = Room.inMemoryDatabaseBuilder(context, TestDatabase.class).build();
//        mUserDao = mDb.getUserDao();
//    }
//
//    @After
//    public void closeDb() throws IOException {
//        mDb.close();
//    }
//
//    @Test
//    public void writeUserAndReadInList() throws Exception {
//        User user = TestUtil.createUser(3);
//        user.setName("george");
//        mUserDao.insert(user);
//        List<User> byName = mUserDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
//    }
//}