package com.flyfish233.freeform

import android.util.Log
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed
class Main : IYukiHookXposedInit {
    override fun onHook() = encase {
        loadSystem {
            "com.android.server.wm.FlexibleWindowUtils".toClass().apply {
                method {
                    name = "isSupportMultiMode"
                }.hook {
                    after {
                        Log.e("FreeForm", "isSupportMultiMode ret: true")
                        resultTrue()
                    }
                }
                "com.android.server.wm.FlexibleWindowManagerService".toClass().apply {
                    method {
                        name = "getMaxWinNum"
                    }.hook {
                        after {
                            val num: Int = 100
                            Log.e("FreeForm", "getMaxWinNum ret: $num")
                            result = num
                        }
                    }
                }
            }
        }
    }
}