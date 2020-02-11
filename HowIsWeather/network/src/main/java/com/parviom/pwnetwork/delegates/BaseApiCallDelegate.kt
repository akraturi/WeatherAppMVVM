package com.parviom.pwnetwork.delegates

import com.parviom.pwnetwork.core.ApiCallback


/**
 *   All api calls are done using delegates
 *   All delegates inherit from this abstract class containing reusable code
 */
abstract class BaseApiCallDelegate<T>(responseType:Class<T>) : ApiCallback<T>(responseType) {

}