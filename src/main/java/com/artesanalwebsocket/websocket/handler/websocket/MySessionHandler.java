package com.artesanalwebsocket.websocket.handler.websocket;

import com.artesanalwebsocket.websocket.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class MySessionHandler implements WebSocketHandler {

    @Autowired
    private RedisService redisService;
    Logger logger = LoggerFactory.getLogger(MySessionHandler.class);

    /**
     * Invoked after WebSocket negotiation has succeeded and the WebSocket connection is
     * opened and ready for use.
     *
     * @param session
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connection established");
    }

    /**
     * Invoked when a new WebSocket message arrives.
     *
     * @param session
     * @param message
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        this.messages.add(message.getPayload().toString());
        redisService.createBook(message.getPayload().toString());
    }

    /**
     * Handle an error from the underlying WebSocket message transport.
     *
     * @param session
     * @param exception
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("Transport error", exception);
    }

    /**
     * Invoked after the WebSocket connection has been closed by either side, or after a
     * transport error has occurred. Although the session may technically still be open,
     * depending on the underlying implementation, sending messages at this point is
     * discouraged and most likely will not succeed.
     *
     * @param session
     * @param closeStatus
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
logger.info("Connection closed");
    }

    /**
     * Whether the WebSocketHandler handles partial messages. If this flag is set to
     * {@code true} and the underlying WebSocket server supports partial messages,
     * then a large WebSocket message, or one of an unknown size may be split and
     * maybe received over multiple calls to
     * {@link #handleMessage(WebSocketSession, WebSocketMessage)}. The flag
     * {@link WebSocketMessage#isLast()} indicates if
     * the message is partial and whether it is the last part.
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}