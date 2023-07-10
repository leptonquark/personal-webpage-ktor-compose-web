package data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class AboutRepository(private val client: HttpClient) {

    suspend fun getAbout() = client.get("/api/about").bodyAsText()

}