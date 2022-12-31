
package com.ihridoydas.simpleapp.data.remote

data class Resource<out T>(val status: Status, val data: T?, val printRequest: String = "", val message: String?) {

    enum class Status {
        /** HTTP 200 Success */
        SUCCESS,
        /** Server, communication error etc **/
        ERROR,
        /** Currently loading */
        LOADING,
        COMPLETED
    }

    companion object {
        fun <T> success(data: T, printRequest: String): Resource<T> {
            return Resource(Status.SUCCESS, data, printRequest, null)
        }

        fun <T> error(message: String, data: T? = null, printRequest: String = ""): Resource<T> {
            return Resource(Status.ERROR, data, printRequest, message)
        }

        fun <T> loading(data: T? = null, printRequest: String): Resource<T> {
            return Resource(Status.LOADING, data, printRequest, null)
        }

        fun <T> completed(data: T? = null, printRequest: String = ""): Resource<T> {
            return Resource(Status.COMPLETED, data, printRequest, null)
        }
    }
}