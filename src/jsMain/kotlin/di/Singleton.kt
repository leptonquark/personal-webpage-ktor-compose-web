package di

import me.tatarka.inject.annotations.Scope
import kotlin.annotation.AnnotationTarget.*


@Scope
@Target(CLASS, FUNCTION, PROPERTY_GETTER)
annotation class Singleton