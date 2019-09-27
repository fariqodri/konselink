package com.trihard.konselink.models.profile

import okhttp3.ResponseBody

//class AccountResponse() {
//    private var _actualBody: AccountBody? = null
//    private var _errorBody: ResponseBody? = null
//
//    constructor(actualBody: AccountBody): this() {
//        this._actualBody = actualBody
//    }
//
//    constructor(errorBody: ResponseBody): this() {
//        this._errorBody = errorBody
//    }
//
//    var actualBody: AccountBody? = _actualBody
//        set(value) {
//            this._actualBody = value
//        }
//
//    var errorBody: ResponseBody? = _errorBody
//        set(value) {
//            this._errorBody = value
//        }
//}

data class AccountResponse(
    var actualBody: AccountBody?,
    var errorBody: ResponseBody?
) {
    constructor(): this(null, null)
    constructor(accountBody: AccountBody): this(accountBody, null)
    constructor(errorBody: ResponseBody): this(null, errorBody)
}