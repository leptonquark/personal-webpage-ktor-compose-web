package title

import di.Singleton
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.tatarka.inject.annotations.Inject
import route.ApiRoute

@Singleton
class TitleService @Inject constructor(private val client: HttpClient) {
        suspend fun getTitle() = client.get(ApiRoute.TITLE).body<TitleText>()
}
