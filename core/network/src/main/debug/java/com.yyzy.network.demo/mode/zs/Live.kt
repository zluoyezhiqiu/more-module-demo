package com.yyzy.test.other.zs

import com.yyzy.base.LogHelper

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.zs
 * @Description: CODE
 * @Date: 2024/1/23
 */
class Live(student: Student) : StudentImpl(student) {

    override fun study() {
        super.study()
        LogHelper.d("毕业之后，在生活中小明上班赚了很多钱！")
    }
}