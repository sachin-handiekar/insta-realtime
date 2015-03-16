import org.jinstagram.Instagram;
import org.jinstagram.realtime.InstagramSubscription;
import org.jinstagram.realtime.SubscriptionResponseData;
import org.jinstagram.realtime.SubscriptionsListResponse;

import java.util.List;

/**
 * @author Sachin Handiekar
 */
public class SubscriptionUtil {

    public static void main(String[] args) throws Exception{
        String clientId = "4869dd72f7c148869747b564b2d6ae32";
        String clientSecret = "8b1968cc0ec94ac8a8db855ff6c49448";

        InstagramSubscription igSub = new InstagramSubscription()
                .clientId(clientId)
                .clientSecret(clientSecret);

        SubscriptionsListResponse subscriptionList = igSub.getSubscriptionList();
        List<SubscriptionResponseData> data = subscriptionList.getData();

        for (SubscriptionResponseData responseData : data) {

            System.out.println("Id : " + responseData.getId());
            System.out.println("Object :" + responseData.getObject());
            System.out.println("Type : " + responseData.getType());


        }
    }
}
