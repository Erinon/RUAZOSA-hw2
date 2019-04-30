package hr.fer.tel.ruazosa.lecture6.notes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

/**
 * Created by dejannovak on 10/04/2018.
 */
class DatabaseHelper(ctx: Context) : OrmLiteSqliteOpenHelper(ctx, "notes.db", null, 1) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, NotesModel.Note::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<NotesModel.Note, Any>(connectionSource, NotesModel.Note::class.java, true)

        onCreate(database, connectionSource)
    }

}