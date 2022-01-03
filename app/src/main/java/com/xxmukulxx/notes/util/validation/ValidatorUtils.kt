package com.techskaud.sampleapp.utilities.validation

import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidatorUtils {

    //PasswordValidator
    fun validPassword(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@*#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

    //EmailValidator

    fun isEmailValid(string: String): Boolean {
        return Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z]{2,5}" +
                    ")+"
        ).matcher(string.trim())
            .matches()
    }

    //MobileValidator

    fun isMobileValid(string: String): Boolean {
        return Pattern.compile("([0-9]{7,15})")
            .matcher(string.trim())
            .matches()
    }

}