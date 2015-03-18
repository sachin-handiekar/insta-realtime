package org.jinstagram.examples;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Sachin Handiekar
 */
public final class SessionHandler {

    private static final Logger logger = Logger.getLogger(SessionHandler.class.getName());

    public static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    public static void sendMessage(String tag) {

        for (Session session : clients) {
            if (session.isOpen()) {

                try {
                    logger.fine("Sending message to " + session.getId());
                    session.getBasicRemote().sendText(tag);
                } catch (IOException e) {
                  logger.severe(e.getMessage());
                }

            }

        }

    }


}
