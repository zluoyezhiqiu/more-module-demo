package com.yyzy.test.other
import com.yyzy.test.other.zs.Live
import com.yyzy.test.other.zs.Power
import com.yyzy.test.other.zs.StudentClass

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other
 * @Description: CODE
 * @Date: 2023/8/8
 */
object TestObj {

    fun load(){
        val studentClass = StudentClass()
        val power = Power(studentClass)
        val live = Live(power)
        live.study()
    }
}