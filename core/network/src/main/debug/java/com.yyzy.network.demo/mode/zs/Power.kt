package com.yyzy.test.other.zs

import com.yyzy.base.LogHelper

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.zs
 * @Description: CODE
 * @Date: 2024/1/23
 */
class Power(student: Student) : StudentImpl(student) {

    override fun study() {
        super.study()
        LogHelper.d("经过三年的学习，小明拥有了强大的力量")
    }
}