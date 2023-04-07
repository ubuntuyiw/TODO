package com.ubuntuyouiwe.todo.data.source.local

import androidx.room.TypeConverter
import com.ubuntuyouiwe.todo.data.dto.local.Response
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ResponseListConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromResponseList(responseList: List<Response>): String {
        return json.encodeToString(responseList)
    }

    @TypeConverter
    fun toResponseList(responseListJson: String): List<Response> {
        return json.decodeFromString(responseListJson)
    }
}