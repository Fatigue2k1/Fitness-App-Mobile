package com.example.fitnessapp.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Workout::class, WorkoutHistory::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutHistoryDao(): WorkoutHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_database"
                )
                    .fallbackToDestructiveMigration()  // This is only for development. Handle migrations properly in production.
                    .build()
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

    @Delete
    fun deleteWorkout(workout: Workout)
}

@Entity(tableName = "workout_history_table")
data class WorkoutHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val workoutName: String,
    val timestamp: Long,
    val details: String
)

@Dao
interface WorkoutHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutHistory(workoutHistory: WorkoutHistory)

    @Query("SELECT * FROM workout_history_table")
    fun getAllWorkoutHistory(): List<WorkoutHistory>
}