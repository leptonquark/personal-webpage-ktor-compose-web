package viewmodel

import data.AboutRepository
import me.tatarka.inject.annotations.Inject

class MainViewModel @Inject constructor(private val aboutRepository: AboutRepository) {

    suspend fun getAbout() = aboutRepository.getAbout()


}