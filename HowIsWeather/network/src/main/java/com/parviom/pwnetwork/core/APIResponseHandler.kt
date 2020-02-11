package com.parviom.pwnetwork.core

import com.parviom.pwnetwork.utils.APIResponseCodes
import utils.AppLogger

class APIResponseHandler {


    companion object{
        val TAG: String?  = APIResponseHandler::class.simpleName

        fun getMessage(status:Int) : String{

            var message:String

            when (status) {
                APIResponseCodes.SUCCESS -> message = "Successful response"
                APIResponseCodes.UNAUTHORIZED_ACCESS -> message = "You are not authorized to access this resource"
                APIResponseCodes.ACCESS_FORBIDDEN  -> message = "You are forbidden to access this resource"
                APIResponseCodes.BAD_REQUEST -> message=  "Missing or incorrect parameter/body passed with api request"
                APIResponseCodes.NOT_FOUND -> message = "The resource/url you are looking for cannot be found"
                APIResponseCodes.SERVER_ERROR -> message = "We are having problem on our servers"
                APIResponseCodes.REQUEST_PARSING_ERROR -> message = "Problem parsing request parameters/body"
                APIResponseCodes.METHOD_NOT_ALLOWED -> message = "Incorrect REST method call on this url"
                APIResponseCodes.API_CONNECTION_ERROR -> message = "Error connecting to this url"

                // Try guessing error by range
                in APIResponseCodes.INFORMATIONAL_RESPONSE_MIN..APIResponseCodes.INFORMATIONAL_RESPONSE_MAX -> message = "Response contains some information why it didn't work"
                in APIResponseCodes.SUCCESS_MIN..APIResponseCodes.SUCCESS_MAX -> message = "Successful api response"
                in APIResponseCodes.REDIRECTION_RESPONSE_MIN..APIResponseCodes.REDIRECTION_RESPONSE_MAX -> message = "API redirection response"
                in APIResponseCodes.CLIENT_ERROR_MIN..APIResponseCodes.CLIENT_ERROR_MAX -> message = "An app side error has occurred"
                in APIResponseCodes.SERVER_ERROR_MIN..APIResponseCodes.SERVER_ERROR_MAX -> message = "A server side error has occurred"
                else -> message = "An unknown error has occurred"
            }

            AppLogger.infoLog(TAG,message)
            return message
        }



    }
}