package com.yyzy.test.other.zs

import com.yyzy.base.LogHelper

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.zs
 * @Description: CODE
 * @Date: 2024/1/23
 */
class StudentClass : Student {

    override fun study() {
        LogHelper.d("该学生在240班级学习...")
    }
}