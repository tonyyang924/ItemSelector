package com.tonyyang.common.itemselector.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.tonyyang.common.itemselector.database.Member
import com.tonyyang.common.itemselector.database.Member.Companion.TABLE_NAME

/**
 * @author tonyyang
 */
@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg members: Member)

    @Update
    fun update(member: Member)

    @Update
    fun update(vararg members: Member)

    @Delete
    fun delete(member: Member)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllMembers(): LiveData<List<Member>>
}