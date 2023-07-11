package api

import data.AboutMessage
import di.Singleton
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.tatarka.inject.annotations.Inject
import route.ApiRoute

@Singleton
class AboutService @Inject constructor(private val client: HttpClient)  {

        suspend fun getAbout() = client.get(ApiRoute.ABOUT).body<AboutMessage>()
}