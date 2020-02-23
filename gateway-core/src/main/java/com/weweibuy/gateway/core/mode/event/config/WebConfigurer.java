package com.weweibuy.gateway.core.mode.event.config;

import com.weweibuy.gateway.core.mode.event.exception.ExceptionMatchHandlerComposite;

/**
 * @author durenhao
 * @date 2020/2/23 18:37
 **/
public interface WebConfigurer {

    /**
     * 增加异常处理
     *
     * @param composite
     */
    default void addExceptionMatchHandler(ExceptionMatchHandlerComposite composite) {

    }

}
