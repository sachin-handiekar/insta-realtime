package org.jinstagram.examples;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Logger;

@ServerEndpoint("/tagSubscription")
public class TagSubscriptionEndpoint {

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @OnOpen
    public void onConnectionOpen(Session session) {
        logger.info("Connection open for " + session.getId());

        SessionHandler.clients.add(session);
    }

    @OnMessage
    public void onMessage(String message) {
        if (message.equals("PING")) {
            logger.info("PING Received!!!");
        }
    }

    @OnClose
    public void onConnectionClose(Session session) {
        SessionHandler.clients.remove(session);
        logger.info("Connection close .... " + session.getId());
    }
}
