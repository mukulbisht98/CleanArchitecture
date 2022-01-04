package com.xxmukulxx.notes.util.validation

import com.template.validations.*
import com.xxmukulxx.notes.util.toast


class Validation() {
    private var validators: ArrayList<Validator>? = null

    init {
        validators = ArrayList()
    }


    fun isEmpty(editable: String?, message: String): Validation {
        validators?.add(EmptyValidator(editable ?: "", message))
        return this
    }

    fun isEmailValid(editable: String?, message: String): Validation {
        validators?.add(EmailValidator(editable ?: "", message))
        return this
    }

    fun isPhoneValid(editable: String?, message: String): Validation {
        validators?.add(PhoneValidator(editable ?: "", message))
        return this
    }

    fun isValidPassword(editable: String?, message: String): Validation {
        validators?.add(PasswordValidator(editable ?: "", message))
        return this
    }


    fun isValid(): Boolean {
        validators.let {
            it?.forEach { validator ->
                if (!validator.isValid()) {
                    toast(validator.message().toString())
                    return false
                }
            }
        }
        return true
    }

}