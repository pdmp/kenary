package me.djangosolutions.kenary.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.djangosolutions.kenary.Database.Daos.UserDao
import me.djangosolutions.kenary.Entity.*

@Database(entities = [(AcademicLevel::class), (Assistance::class), (City::class), (Gender::class), (Institution::class), (Phone::class), (Product::class), (Rate::class), (Role::class), (Session::class), (Study::class), (Subject::class), (SubjectTopic::class), (Topic::class), (Tutorial::class), (TypePayment::class), (TypeSession::class), (User::class)], version = 1)
abstract class KenaryDatabase: RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: KenaryDatabase? = null

        fun getDatabase(context: Context): KenaryDatabase? {
            if (INSTANCE == null){
                synchronized(KenaryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            KenaryDatabase::class.java, "database")
                            .build()
                }
            }
            return INSTANCE
        }

        fun nukeInstance() {
            INSTANCE = null
        }
    }
}