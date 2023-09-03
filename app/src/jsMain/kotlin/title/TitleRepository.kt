package title

import di.Singleton
import me.tatarka.inject.annotations.Inject

@Singleton
class TitleRepository @Inject constructor(private val service: TitleService) {
    suspend fun getTitle() = service.getTitle().text
}
