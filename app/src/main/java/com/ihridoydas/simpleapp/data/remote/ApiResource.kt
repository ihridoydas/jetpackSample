
package com.ihridoydas.simpleapp.data.remote


class ApiResource<T>(
    val printRequest: String,
    val apiResultPattern: ApiResultPattern = ApiResultPattern.NotSet,
    val data: T? = null,
   // val errorData: NPBApiErrorResponse? = null,
    // val message: AlertMessage = AlertMessage(title = "", body = "")
) {
    enum class ApiResultPattern {
        // 成功（Return-Code: 0）
        Success,
        // NPB-web側で処理失敗（Return-Code：1）
        NpbApiProcessFailure,
        // NPB-web側が閉局中（Return-Code：1かつhttpStatusCode：503）
        NpbApiInfraUnavailable,
        // Retrofit側でRequest失敗
        RetrofitRequestFailure,
        // ResponseのJsonのDecode失敗
        ResponseJsonDecodeFailure,
        // レスポンスにあるはずのリターンコードが含まれていない。
        DoseNotContainReturnCode,
        // リクエストのキャンセル
        RequestCancel,
        // 未設定
        NotSet
    }
}
