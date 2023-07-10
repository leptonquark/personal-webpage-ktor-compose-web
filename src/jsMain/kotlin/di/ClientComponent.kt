package di

import io.ktor.client.*
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import ui.MainScreen

@Singleton
@Component
abstract class ClientComponent {
    abstract val mainScreen: MainScreen

    @Provides
    @Singleton
    fun httpClient(): HttpClient = HttpClient()
}