package com.ubuntuyouiwe.todo.data.source.local

import kotlinx.serialization.Serializable
import java.sql.Timestamp
@Serializable
data class SerializableTimestamp(val value: Long) {
    fun toTimestamp(): Timestamp = Timestamp(value)
    constructor(timestamp: Timestamp) : this(timestamp.time)
}
