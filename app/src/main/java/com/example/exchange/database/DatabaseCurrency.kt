package com.example.exchange.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exchange.models.Currency


@Entity(tableName = "currencies")
data class DatabaseCurrency(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "date_end") val dateEnd: String,
)

fun List<DatabaseCurrency>.asDomainModel(): List<Currency> {
    return map {
        Currency(
            id = it.id,
            name = it.name,
            dateEnd = it.dateEnd,
        )
    }
}


