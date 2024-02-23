package com.paranid5.star_wars_travel.data.sqldelight

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.paranid5.star_wars_travel.data.Planets

fun SqlDelightClient(context: Context): SqlDriver =
    AndroidSqliteDriver(
        schema = Planets.Schema,
        context = context,
        name = "planets.db",
        callback = object : AndroidSqliteDriver.Callback(Planets.Schema) {
            override fun onOpen(db: SupportSQLiteDatabase) {
                db.setForeignKeyConstraintsEnabled(true)
            }
        }
    )