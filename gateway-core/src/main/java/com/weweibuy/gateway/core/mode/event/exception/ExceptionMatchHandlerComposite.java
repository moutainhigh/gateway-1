package com.weweibuy.gateway.core.mode.event.exception;

import org.springframework.web.server.ServerWebExchange;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author durenhao
 * @date 2020/2/23 18:39
 **/
public class ExceptionMatchHandlerComposite {

    private final List<ExceptionMatchHandler> handlers = new LinkedList<>();

    private final Map<Class, ExceptionMatchHandler> handlerCache =
            new ConcurrentHashMap<>(8);

    public void addHandler(List<ExceptionMatchHandler> compensateTypeResolver) {
        handlers.addAll(compensateTypeResolver);
    }

    public void addHandler(ExceptionMatchHandler compensateTypeResolver) {
        handlers.add(compensateTypeResolver);
    }

    /**
     * 获取异常处理器
     *
     * @param exchange
     * @param ex
     * @return
     */
    public ExceptionMatchHandler getExceptionHandler(ServerWebExchange exchange, Throwable ex) {
        Class<? extends Throwable> clazz = ex.getClass();
        ExceptionMatchHandler result = this.handlerCache.get(ex.getClass());
        if (result == null) {
            for (ExceptionMatchHandler handler : this.handlers) {
                if (handler.match(exchange, ex)) {
                    result = handler;
                    this.handlerCache.put(clazz, result);
                    break;
                }
            }
        }
        return result;
    }
}
