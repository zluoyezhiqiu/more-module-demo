//package com.yyzy.database
//import android.content.Context
//import android.os.SystemClock
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.withTransaction
//import androidx.sqlite.db.SupportSQLiteDatabase
//import java.lang.reflect.InvocationHandler
//import java.lang.reflect.Method
//import java.lang.reflect.Proxy
//
///**
// * @Author: ljl
// * @Email: ljl@dofun.cc
// * @ClassName: com.dofun.forum.model.dao
// * @Description: https://blog.csdn.net/vistaup/article/details/121418352
// * @Date: 2023/4/6
// */
//class DatabaseManager (context: Context): DatabaseService {
//
//    private val getAppDataBase: AppDataBase by lazy {
//        Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
//            .openHelperFactory(SafeHelperFactory(PASSWORD.toCharArray()))
//            .addCallback(CreatedCallBack)
//            .addMigrations(Migration4To5,Migration5To6)
//            .apply {
//                //在出现升级异常时，重新创建数据库表
//                if (!AutoApi.isDebugMode) fallbackToDestructiveMigration()
//                //fallbackToDestructiveMigration()
//            }.build()
//    }
//
//    private object CreatedCallBack : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            //在新装app时会调用，调用时机为数据库build()之后，数据库升级时不调用此函数
//            LogHelper.w(DB_TAG,"RoomDatabase CreatedCallBack !!!")
//        }
//    }
//
//    override val resCheckoutDao: ICommonResourceCheckDao by lazy {
//        createDaoProxy("model_check_db", getAppDataBase.getCommonResourceCheckDao())
//    }
//
//    override val modelResDao: ICarModelDao by lazy {
//        createDaoProxy("model_db", getAppDataBase.getDownloadCarModelDao())
//    }
//
//    override val sceneResDao: IUserSceneDao by lazy {
//        createDaoProxy("scene_db", getAppDataBase.getDownloadSceneDao())
//    }
//
//    override val modelComponentDao: IModelPluginDao by lazy {
//        createDaoProxy("plugin_db", getAppDataBase.getModelPluginDao())
//    }
//
//    override suspend fun <R> withTransaction(block: suspend () -> R): R {
//        return getAppDataBase.withTransaction {
//            block.invoke()
//        }
//    }
//
//    private inline fun <reified T> createDaoProxy(tag: String, dao: T): T {
//        return Proxy.newProxyInstance(
//            T::class.java.classLoader, arrayOf(T::class.java), DaoLogProxy(tag, dao)
//        ) as T
//    }
//
//    private class DaoLogProxy(
//        tag: String,
//        private val target: Any?,
//    ) : InvocationHandler {
//        private val logTag: String = "DBCall-$tag"
//        override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
//            val start = SystemClock.elapsedRealtime()
//            val invoke = method.invoke(target, *args ?: emptyArray())
//            LogHelper.i(logTag, "DBCall ${method.name} ${SystemClock.elapsedRealtime() - start}ms")
//            return invoke
//        }
//    }
//}