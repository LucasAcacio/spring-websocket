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
import com.artesanalwebsocket.websocket.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MySessionHandler implements WebSocketHandler {

    @Autowired
    private RedisService redisService;

    private List<String> messageBuffer = new ArrayList<>();
    private static final int BATCH_SIZE = 1000;

    public MySessionHandler(RedisService redisService) {
        this.redisService = redisService;
    }

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
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        String payload = message.getPayload().toString();
        messageBuffer.add(payload);
        if (messageBuffer.size() >= BATCH_SIZE) {
            processBatch();
        }
    }

    private void processBatch() {
        redisService.createBooks(new ArrayList<>(messageBuffer.stream().map(Book::new).collect(Collectors.toList())));
        messageBuffer.clear();
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
