package com.yyzy.test.other.poxy

import com.yyzy.base.LogHelper

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.poxy
 * @Description: CODE
 * @Date: 2024/1/18
 */
class AnxietyRole1 : AnxietyInterface {

    override fun qiDuan() {
        LogHelper.dTwo("上班经常感觉气短！")
    }

    override fun touYun() {
        LogHelper.dTwo("气短状态加剧中可能会头晕")
    }

    override fun buNengJiZhongZhuYiLi() {
        LogHelper.dTwo("集中注意力状态下降？")
    }
}