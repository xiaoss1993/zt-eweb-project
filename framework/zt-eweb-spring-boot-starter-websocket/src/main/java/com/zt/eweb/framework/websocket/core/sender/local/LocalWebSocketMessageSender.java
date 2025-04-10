package com.zt.eweb.framework.websocket.core.sender.local;

import com.zt.eweb.framework.websocket.core.sender.AbstractWebSocketMessageSender;
import com.zt.eweb.framework.websocket.core.sender.WebSocketMessageSender;
import com.zt.eweb.framework.websocket.core.session.WebSocketSessionManager;

/**
 * 本地的 {@link WebSocketMessageSender} 实现类
 * <p>
 * 注意：仅仅适合单机场景！！！
 *
 *
 */
public class LocalWebSocketMessageSender extends AbstractWebSocketMessageSender {

    public LocalWebSocketMessageSender(WebSocketSessionManager sessionManager) {
        super(sessionManager);
    }

}
