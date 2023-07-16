package api

import data.ContactMeItem
import di.Singleton
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.tatarka.inject.annotations.Inject
import route.ApiRoute

@Singleton
class ContactMeService @Inject constructor(private val client: HttpClient) {
        suspend fun getContactMeItems() = client.get(ApiRoute.CONTACT_ME).body<List<ContactMeItem>>()
}
