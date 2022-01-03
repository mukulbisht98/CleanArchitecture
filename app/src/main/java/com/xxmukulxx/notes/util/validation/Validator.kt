package com.template.validations

interface Validator {
    fun isValid(): Boolean
    fun message(): String?
}