package com.test.core_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "request_info")
data class RequestInfoEntity(
    @PrimaryKey
    val requestPart: String,
)