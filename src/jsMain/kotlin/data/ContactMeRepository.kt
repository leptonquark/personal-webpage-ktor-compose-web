package data

import api.ContactMeService
import di.Singleton
import me.tatarka.inject.annotations.Inject

@Singleton
class ContactMeRepository @Inject constructor(private val service: ContactMeService) {
    suspend fun getContactMeItems() = service.getContactMeItems()
}
