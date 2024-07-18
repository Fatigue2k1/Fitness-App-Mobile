
//
//import android.content.Context
//import androidx.room.Dao
//import androidx.room.Database
//import androidx.room.Entity
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.PrimaryKey
//import androidx.room.Query
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [Workout::class], version = 1)
//abstract class WorkoutDatabase : RoomDatabase() {
//    abstract fun workoutDao(): WorkoutDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: WorkoutDatabase? = null
//
//        fun getDatabase(context: Context): WorkoutDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    WorkoutDatabase::class.java,
//                    "workout_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
//
//@Entity(tableName = "workout_table")
//data class Workout(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val name: String,
//    val description: String
//)
//
//@Dao
//interface WorkoutDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWorkout(workout: Workout)
//
//    @Query("SELECT * FROM workout_table")
//    fun getAllWorkouts(): List<Workout>
//}