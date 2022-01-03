package com.template.validations

import com.techskaud.sampleapp.utilities.validation.ValidatorUtils


class PasswordValidator(private val editable: String, val message: String) :
    Validator {
    override fun isValid(): Boolean {
        return ValidatorUtils.validPassword(editable.trim())
    }

    override fun message(): String {
        return message
    }
}