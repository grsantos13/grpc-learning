package br.com.zup.shared.conf

import io.micronaut.context.MessageSource
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.i18n.ResourceBundleMessageSource
import io.micronaut.runtime.context.CompositeMessageSource
import javax.inject.Singleton

@Factory
class I18n {

    @Bean
    @Singleton
    fun messageSource(): MessageSource {
        return CompositeMessageSource(
            listOf(ResourceBundleMessageSource("messages"))
        )
    }
}