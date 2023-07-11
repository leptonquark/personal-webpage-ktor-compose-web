package viewmodel

import data.AboutRepository
import di.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import me.tatarka.inject.annotations.Inject

data class MainState(val about: String? = null)

@Singleton
class MainViewModel @Inject constructor(private val aboutRepository: AboutRepository) {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)


    val state = flow {
            val about = aboutRepository.getAbout()
            emit(MainState(about))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = MainState()
        )


}