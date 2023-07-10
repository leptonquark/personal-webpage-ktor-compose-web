package viewmodel

import data.AboutRepository

class MainViewModel(private val aboutRepository: AboutRepository) {

    suspend fun getAbout() = aboutRepository.getAbout()


}