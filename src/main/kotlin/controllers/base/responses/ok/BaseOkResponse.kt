package controllers.base.responses.ok

import controllers.base.responses.BaseResponse

open class BaseOkResponse(httpStatus: Int = 200): BaseResponse("success", httpStatus = httpStatus)