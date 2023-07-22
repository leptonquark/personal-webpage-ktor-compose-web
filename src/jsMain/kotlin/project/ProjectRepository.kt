package project

import di.Singleton
import me.tatarka.inject.annotations.Inject

@Singleton
class ProjectRepository @Inject constructor(private val service: ProjectService) {
    suspend fun getProjects() = service.getProjects()
}
