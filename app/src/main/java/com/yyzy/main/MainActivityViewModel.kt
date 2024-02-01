package com.yyzy.main
import androidx.lifecycle.ViewModel
import com.yyzy.common.util.LogHelper
import com.yyzy.common.util.task.repeatOnViewLifecycleOnAppMain
import com.yyzy.common.util.task.requestMain
import com.yyzy.data.repo.LifeTrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.main
 * @Description: CODE
 * @Date: 2024/2/1
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val lifeTrackRepository: LifeTrackRepository
) : ViewModel() {

    fun print() {
        requestMain {
            lifeTrackRepository.saveLifeTrackEntity()
            repeatOnViewLifecycleOnAppMain(lifeTrackRepository.getLifeTrackFlow()) {
                LogHelper.dTwo(it.joinToString { entity ->
                    entity.toString()
                })
            }
        }
    }
}