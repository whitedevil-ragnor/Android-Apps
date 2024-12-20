package com.meow.wishlist.room_setup


import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "Wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    @ColumnInfo(name = "title-column")
    var title:String="",
    @ColumnInfo(name = "des-column")
    var description:String=""
)

//dao set up
@Dao
abstract class wishDao{
    @Insert
    abstract suspend fun wishInsert(wish: Wish)

    @Query("Select * from `Wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun wishUpdate(wish: Wish)

    @Delete
    abstract suspend fun wishDelete(wish: Wish)

    @Query("Select * from `wish-table` where id=:id")
    abstract fun getWish(id:Long): Flow<Wish>

}

//setting up database
@Database(entities = [Wish::class], version = 1, exportSchema = false)
abstract class WishDataBase:RoomDatabase(){
    abstract fun wishDao(): wishDao
}

class  WishRepository(private val wishdao: wishDao){
    suspend fun wishInsert(wish: Wish){
        wishdao.wishInsert(wish)
    }
    fun getAllWishes(): Flow<List<Wish>> = wishdao.getAllWishes()

    suspend fun wishUpdate(wish: Wish){
        wishdao.wishUpdate(wish)
    }
    suspend fun wishDelete(wish: Wish){
        wishdao.wishDelete(wish)
    }
    fun getWish(id: Long):Flow<Wish>{
        return wishdao.getWish(id)
    }

}


