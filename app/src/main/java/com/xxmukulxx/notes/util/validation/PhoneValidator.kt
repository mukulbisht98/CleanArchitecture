package com.template.validations

import com.techskaud.sampleapp.utilities.validation.ValidatorUtils


class PhoneValidator(private val editable: String, val message: String) :
    Validator {
    override fun isValid(): Boolean {
        return ValidatorUtils.isMobileValid(editable.trim())
    }

    override fun message(): String {
        return message
    }
}