package contactme

import di.Singleton
import me.tatarka.inject.annotations.Inject

@Singleton
class ContactMeRepository @Inject constructor(private val service: ContactMeService) {
    suspend fun getContactMeLinks() = service.getContactMeLinks()
}
