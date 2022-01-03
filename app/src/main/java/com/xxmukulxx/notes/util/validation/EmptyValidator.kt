package com.template.validations

class EmptyValidator(private val editable: String, val message: String) : Validator {
    override fun isValid(): Boolean {
        return editable.trim().isNotEmpty()
    }

    override fun message(): String {
        return message
    }
}