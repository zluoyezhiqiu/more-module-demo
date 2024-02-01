package com.yyzy.database
import android.content.Context
import android.os.SystemClock
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yyzy.common.util.LogHelper
import com.yyzy.database.dao.LifeTrackDAO
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.dofun.forum.model.dao
 * @Description: https://blog.csdn.net/vistaup/article/details/121418352
 * @Date: 2023/4/6
 */
private const val DB_TAG = "DB-CALL"
class LifeDatabase(context: Context) {

    private val getAppDataBase: NiaDatabase by lazy {
        Room.databaseBuilder(context, NiaDatabase::class.java, "DB_NAME")
            .addCallback(CreatedCallBack)
            .fallbackToDestructiveMigration().build()
    }

    private object CreatedCallBack : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            //在新装app时会调用，调用时机为数据库build()之后，数据库升级时不调用此函数
            LogHelper.w(DB_TAG, "RoomDatabase CreatedCallBack !!!")
        }
    }

    val resCheckoutDao: LifeTrackDAO by lazy {
        createDaoProxy("life_track", getAppDataBase.lifeTrackDAO())
    }

    private inline fun <reified T> createDaoProxy(tag: String, dao: T): T {
        return Proxy.newProxyInstance(
            T::class.java.classLoader, arrayOf(T::class.java), DaoLogProxy(tag, dao)
        ) as T
    }

    private class DaoLogProxy(
        tag: String,
        private val target: Any?,
    ) : InvocationHandler {
        private val logTag: String = "DBCall-$tag"
        override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
            val start = SystemClock.elapsedRealtime()
            val invoke = method.invoke(target, *args ?: emptyArray())
            LogHelper.i(logTag, "DBCall ${method.name} ${SystemClock.elapsedRealtime() - start}ms")
            return invoke
        }
    }
}