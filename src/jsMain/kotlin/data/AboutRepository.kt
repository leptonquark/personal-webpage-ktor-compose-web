package data

import di.Singleton
import route.ApiRoute
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import me.tatarka.inject.annotations.Inject

@Singleton
class AboutRepository @Inject constructor(private val client: HttpClient) {

    suspend fun getAbout() = client.get(ApiRoute.ABOUT).bodyAsText()

}