import di.ClientComponent
import di.create


suspend fun main() {
    val clientComponent = ClientComponent::class.create()
    val mainScreen = clientComponent.mainScreen
    mainScreen.render()
}
