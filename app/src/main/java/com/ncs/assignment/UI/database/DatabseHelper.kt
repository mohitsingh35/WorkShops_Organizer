package com.ncs.assignment.UI.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ncs.assignment.UI.models.WorkshopItem

class DatabseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "WorkshopDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Workshops"
        private const val ID = "id"
            private const val NAME = "name"
        private const val IMGURL = "imageUrl"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = """
            CREATE TABLE $TABLE_NAME (
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $NAME TEXT,
                $IMGURL TEXT
            )
        """.trimIndent()
        db.execSQL(createTableSQL)
        insertDefaultTasks(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    @SuppressLint("Range")
    fun getallWorkshops(): List<WorkshopItem> {
        val List = mutableListOf<WorkshopItem>()
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(ID))
                val name = cursor.getString(cursor.getColumnIndex(NAME))
                val imgurl = cursor.getString(cursor.getColumnIndex(IMGURL))
                List.add(WorkshopItem(id, name, imgurl))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return List
    }
    private fun insertDefaultTasks(db: SQLiteDatabase) {
        val values = ContentValues()
        values.put(NAME, "Web Development Workshop")
        values.put(IMGURL, "https://www.creativeitinstitute.com/images/course/course_1663052056.jpg")
        db.insert(TABLE_NAME, null, values)

        values.clear()

        values.put(NAME, "Android Development Workshop")
        values.put(IMGURL, "https://www.simplilearn.com/ice9/free_resources_article_thumb/How_to_Become_an_Android_Developer.jpg")
        db.insert(TABLE_NAME, null, values)

        values.clear()

        values.put(NAME, "BlockChain Development Workshop")
        values.put(IMGURL, "https://www.xilinx.com/content/xilinx/en/products/design-tools/resources/the-developers-guide-to-blockchain-development/_jcr_content/root/parsysFullWidth/xilinxflexibleslab/xilinxflexibleslab-parsys/xilinxcolumns_388240228/childParsys-0/xilinxcolumns/childParsys-0/xilinximage_copy_cop.img.png/1644357925388.png")
        db.insert(TABLE_NAME, null, values)

        values.clear()

        values.put(NAME, "React Native")
        values.put(IMGURL, "https://bs-uploads.toptal.io/blackfish-uploads/components/blog_post_page/content/cover_image_file/cover_image/1279225/retina_1708x683_0521-react-redux-and-immutablejs-Waldek_Newsletter-993b50f4ae56e9ee6e024a309c23a6c4.png")
        db.insert(TABLE_NAME, null, values)

        values.clear()

        values.put(NAME, "Kotlin Development Workshop")
        values.put(IMGURL, "https://www.jrebel.com/sites/default/files/image/2021-01/what%20is%20kotlin%20banner%20image.png")
        db.insert(TABLE_NAME, null, values)

        values.clear()

        values.put(NAME, "Python Development Workshop")
        values.put(IMGURL, "https://www.inexture.com/wp-content/uploads/2023/07/choose-python-development-company-1100-x-600-%E2%80%93-2.png")
        db.insert(TABLE_NAME, null, values)

        values.clear()

        values.put(NAME, "Java Development Workshop")
        values.put(IMGURL, "https://www.jrebel.com/sites/default/files/image/2020-05/image-blog-revel-top-java-tools.jpg")
        db.insert(TABLE_NAME, null, values)

        values.clear()

        values.put(NAME, "C++ Development Workshop")
        values.put(IMGURL, "https://fgp.dev/static/media/CDevelopmentBanner.ae45732e.jpg")
        db.insert(TABLE_NAME, null, values)

        values.clear()


    }
}
