package api2.utils

import grails.util.Holders
import org.springframework.context.MessageSource

class ErrorMessageUtils {
    static private MessageSource messageSource = Holders.applicationContext.getBean("messageSource") as MessageSource
    static private Locale locale = Locale.getDefault()

    static String getMessage(String code) { messageSource.getMessage(code, null, locale) }
}
