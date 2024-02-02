package com.yyzy.test.other.zs

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.zs
 * @Description: CODE
 * @Date: 2024/1/23
 */
open class StudentImpl(private val student: Student) : Student {

    override fun study() {
        student.study()
    }
}