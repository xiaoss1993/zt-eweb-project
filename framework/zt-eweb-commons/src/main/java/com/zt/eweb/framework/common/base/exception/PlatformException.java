/*
 * MIT License
 *
 * Copyright (c) 2023 北京凯特伟业科技有限公司
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zt.eweb.framework.common.base.exception;

import com.zt.eweb.framework.common.base.locale.MessageLocator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class PlatformException extends RuntimeException {
    private String code;
    private String errorCode;
    private String errorMsg;
    private Object[] errorParam;

    public PlatformException() {
        super();
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }

    public PlatformException(String message, PlatformExceptionEnum exceptionEnum) {
        super(message);
        this.errorCode = PlatformExceptionEnum.getDefault(exceptionEnum).getCode();
        this.errorMsg = MessageLocator.getMessage(this.errorCode, message);
    }

    public PlatformException(String message, PlatformExceptionEnum exceptionEnum, Throwable cause) {
        super(message, cause);
        this.code=PlatformExceptionEnum.getDefault(exceptionEnum).toString();
        this.errorCode = PlatformExceptionEnum.getDefault(exceptionEnum).getCode();
        this.errorMsg = MessageLocator.getMessage(this.errorCode, message);
    }
    public PlatformException(String message, PlatformExceptionEnum exceptionEnum, Object[] params) {
        super(message);
        this.code=PlatformExceptionEnum.getDefault(exceptionEnum).toString();
        this.errorCode = PlatformExceptionEnum.getDefault(exceptionEnum).getCode();
        this.errorMsg = MessageLocator.getMessage(this.errorCode,params, message);
    }

    public PlatformException(String message, PlatformExceptionEnum exceptionEnum, Object[] params, Throwable cause) {
        super(message, cause);
        this.code=PlatformExceptionEnum.getDefault(exceptionEnum).toString();
        this.errorCode = PlatformExceptionEnum.getDefault(exceptionEnum).getCode();
        this.errorMsg = MessageLocator.getMessage(this.errorCode, params, message);
        this.errorParam = params;
    }
    public PlatformException(String message, PlatformExceptionEnum exceptionEnum, HttpServletRequest request) {
        this(message,exceptionEnum,request,null);
    }
    public PlatformException(String message, PlatformExceptionEnum exceptionEnum, HttpServletRequest request, Throwable cause) {
        super(message, cause);
        Map<String,Object> params=new HashMap<>();
        Enumeration<String> enumerations=request.getParameterNames();
        while(enumerations.hasMoreElements()){
            String key=enumerations.nextElement();
            params.put(key,request.getParameter(key));
        }
        String uri = request.getRequestURI();
        //todo 这里先注释掉
//        Enumeration<String> enumeration = request.getHeaders(SessionFilter.X_AUTH_TOKEN);
        String tokenId="";
//        if (enumeration.hasMoreElements()) {
//            tokenId = enumeration.nextElement();
//        }
        this.code=PlatformExceptionEnum.getDefault(exceptionEnum).toString();
        this.errorCode = PlatformExceptionEnum.getDefault(exceptionEnum).getCode();
        this.errorMsg = MessageLocator.getMessage(this.errorCode, new Object[]{uri,tokenId,params}, message);
        this.errorParam = new Object[]{uri,tokenId,params};
    }

    @Override
    public String getLocalizedMessage() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Object[] getErrorParam() {
        return errorParam;
    }

    public String getCode() {
        return code;
    }
}
