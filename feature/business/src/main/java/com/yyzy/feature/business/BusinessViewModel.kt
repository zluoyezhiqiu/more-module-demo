package com.yyzy.feature.business

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.yyzy.common.util.task.repeatOnViewLifecycleOnViewModel
import com.yyzy.common.util.task.requestMain
import com.yyzy.data.repo.LifeTrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.main
 * @Description: CODE
 * @Date: 2024/2/1
 */
@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val lifeTrackRepository: LifeTrackRepository
) : ViewModel(), DefaultLifecycleObserver {

    private val _messageFlow = MutableSharedFlow<String>()
    val messageFlow: MutableSharedFlow<String>
        get() = _messageFlow

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        requestMain {
            lifeTrackRepository.saveLifeTrackEntity()
            repeatOnViewLifecycleOnViewModel(lifeTrackRepository.getLifeTrackFlow()) {
                _messageFlow.emit(it.joinToString { entity ->
                    entity.toString()
                })
            }
        }
    }
}