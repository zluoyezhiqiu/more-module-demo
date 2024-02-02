package com.yyzy.test.other.poxy

import com.yyzy.base.LogHelper

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.poxy
 * @Description: CODE
 * @Date: 2024/1/18
 */
class AnxietyRole2 : AnxietyInterface {

    override fun qiDuan() {
        LogHelper.dTwo("吃饭时感觉气短！")
    }

    override fun touYun() {
        LogHelper.dTwo("气短状态加剧中可能会站立不稳")
    }

    override fun buNengJiZhongZhuYiLi() {
        LogHelper.dTwo("视力下降，近距离模糊？")
    }
}