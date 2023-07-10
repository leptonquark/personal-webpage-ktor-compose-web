package viewmodel

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class MainViewModel(private val client: HttpClient) {

    suspend fun getAbout() = client.get("/api/about").bodyAsText()


}