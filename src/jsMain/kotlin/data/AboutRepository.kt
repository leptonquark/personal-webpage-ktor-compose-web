package data

import api.AboutService
import di.Singleton
import me.tatarka.inject.annotations.Inject

@Singleton
class AboutRepository @Inject constructor(private val service: AboutService) {

    suspend fun getAbout() = service.getAbout().message

}