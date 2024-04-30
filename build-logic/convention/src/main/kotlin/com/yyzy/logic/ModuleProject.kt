package com.yyzy.logic

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.logic
 * @Description: CODE
 * @Date: 2024/4/30
 */
sealed class ModuleProject(dirPath: String, moduleName: String) {
    val path: String = "$dirPath:$moduleName"
}

sealed class CoreModule(moduleName: String) : ModuleProject(":core", moduleName) {
    object Database : CoreModule("database")
    object Network : CoreModule("network")
}
