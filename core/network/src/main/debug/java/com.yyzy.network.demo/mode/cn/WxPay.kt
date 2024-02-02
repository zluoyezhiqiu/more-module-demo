package com.yyzy.test.other.cn

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.cn
 * @Description: CODE
 * @Date: 2024/1/23
 */
class WxPay : Pay {
    override fun pay(money: Float) {

    }
}

class ZfbPay : Pay {
    override fun pay(money: Float) {

    }
}

class PayManager : Pay {

    private var pay: Pay? = null

    fun setPayWay(pay: Pay) {
        this.pay = pay
    }

    override fun pay(money: Float) {
        pay?.pay(money)
    }
}

object PayObj {

    fun test() {
        val payManager = PayManager()
        payManager.setPayWay(WxPay())
        payManager.pay(10f)

        payManager.setPayWay(ZfbPay())
        payManager.pay(12f)
    }
}