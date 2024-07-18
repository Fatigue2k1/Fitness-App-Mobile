package com.example.fitnessapp.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Workout::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val email: String,
    val password: String
)

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User?)

    @Query("SELECT * FROM user_table WHERE email = :userEmail")
    fun getUserByEmail(userEmail: String): User?

    @Query("UPDATE user_table SET password = :newPassword WHERE email = :email")
    fun updatePassword(email: String, newPassword: String)
}

@Entity(tableName = "workout_table")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String
)

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workout: Workout)

    @Query("SELECT * FROM workout_table")
    fun getAllWorkouts(): List<Workout>
}