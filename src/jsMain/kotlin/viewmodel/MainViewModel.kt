package viewmodel

import data.AboutRepository
import di.Singleton
import me.tatarka.inject.annotations.Inject

@Singleton
class MainViewModel @Inject constructor(private val aboutRepository: AboutRepository) {

    suspend fun getAbout() = aboutRepository.getAbout()


}