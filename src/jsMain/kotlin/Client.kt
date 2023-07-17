
import di.ClientComponent
import di.create


fun main() {
    val component = ClientComponent::class.create()
    val mainScreen = component.mainScreen
    mainScreen.initialize()
}
