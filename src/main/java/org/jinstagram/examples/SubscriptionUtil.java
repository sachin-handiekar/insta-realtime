package org.jinstagram.examples;

import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.realtime.InstagramSubscription;
import org.jinstagram.realtime.SubscriptionResponse;
import org.jinstagram.realtime.SubscriptionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sachin Handiekar
 */
public class SubscriptionUtil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createSubscription(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


    private void createSubscription(HttpServletRequest req, HttpServletResponse resp) throws InstagramException {
        String clientId = "-- clientId --";
        String clientSecret = "-- clientSecret --";

        InstagramSubscription igSub = new InstagramSubscription()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .object(SubscriptionType.TAGS)
                .objectId("jinstagram")
                .aspect("media")
                .callback("your callback url")
                .verifyToken("someverifyToken");


        SubscriptionResponse subscriptionResponse = igSub.createSubscription();


    }


}
