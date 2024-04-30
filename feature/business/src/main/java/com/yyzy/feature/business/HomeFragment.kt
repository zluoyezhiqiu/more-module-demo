package com.yyzy.feature.business

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yyzy.common.Navigation
import com.yyzy.feature.business.databinding.BusFragHomeBinding

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.feature.business
 * @Description: CODE
 * @Date: 2024/3/7
 */
class HomeFragment : Fragment() {

    private var _homeBinding: BusFragHomeBinding? = null
    private inline val homeBinding: BusFragHomeBinding
        get() = _homeBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _homeBinding = BusFragHomeBinding.inflate(inflater)
        return _homeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBinding.fragHomeTv.setOnClickListener {
            navigateToPlant()
        }
    }

    private fun navigateToPlant() {
        findNavController().navigate("${Navigation.Routes.SETTING}/来自HomeFragment的参数")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _homeBinding = null
    }
}