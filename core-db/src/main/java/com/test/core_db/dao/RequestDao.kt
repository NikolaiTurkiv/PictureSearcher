package com.test.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.core_db.entities.RequestInfoEntity

@Dao
interface RequestDao {

    @Query("SELECT * FROM request_info WHERE requestPart LIKE '%' || :partRequest || '%' LIMIT 5")
    suspend fun getRequestList(partRequest:String): List<RequestInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(partRequest: RequestInfoEntity)

    @Query("DELETE FROM request_info")
    suspend fun removeAll()

}
