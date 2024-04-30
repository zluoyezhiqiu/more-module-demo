package com.yyzy.logic

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.runtimeOnlyProject(moduleProject: ModuleProject): Dependency? =
    add("runtimeOnly", project("path" to moduleProject.path))

fun DependencyHandler.implementationProject(moduleProject: ModuleProject): Dependency? =
    add("implementation", project("path" to moduleProject.path))
