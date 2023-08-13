package project

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.tatarka.inject.annotations.Inject
import route.ApiRoute

class ProjectService @Inject constructor(private val client: HttpClient) {
    suspend fun getProjects() = client.get(ApiRoute.PROJECTS).body<ProjectList>()
}
