package com.yyzy.logic
import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.build.logic
 * @Description: CODE
 * @Date: 2024/2/1
 */
fun DefaultTask.loge(msg: String) {
    logger.log(LogLevel.ERROR, ">>> LogicTask ${project.name} $name $msg")
}

fun Plugin<Project>.printlnLogic(msg: String) {
    println(">>> LogicTask ${javaClass.simpleName} $msg")
}
