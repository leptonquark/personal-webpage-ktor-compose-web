package data

import api.AboutService
import di.Singleton
import route.ApiRoute
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.tatarka.inject.annotations.Inject

@Singleton
class AboutRepository @Inject constructor(private val service: AboutService) {

    suspend fun getAbout() = service.getAbout().message

}