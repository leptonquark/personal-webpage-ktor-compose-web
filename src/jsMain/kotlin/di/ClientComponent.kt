package di

import io.ktor.client.*
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import ui.MainScreen

@Component
abstract class ClientComponent {
    abstract val mainScreen: MainScreen

    @Provides
    fun httpClient(): HttpClient = HttpClient()
}