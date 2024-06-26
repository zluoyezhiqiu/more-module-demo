package com.yyzy.feature.setting

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yyzy.common.Navigation
import com.yyzy.feature.setting.databinding.SetFragSettingBinding

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.feature.business
 * @Description: CODE
 * @Date: 2024/3/7
 */
class SettingFragment : Fragment() {

    private var _setBinding: SetFragSettingBinding? = null
    private inline val setBinding: SetFragSettingBinding
        get() = _setBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _setBinding = SetFragSettingBinding.inflate(inflater)
        return _setBinding?.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBinding.fragSettingTv.apply {
            val busId = arguments?.getString(Navigation.Arguments.SETTING_ID)
            text = " SettingFragment ---> \n $busId"

            setOnClickListener {
                findNavController().navigate(Navigation.Routes.ACTIVITY_BUS)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _setBinding = null
    }
}