package org.javadv.service;

import org.javadv.domain.Subscription;
import org.javadv.impl.exception.SubscriptionServiceException;

import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);

    Subscription updateSubscription(Subscription subscription) throws SubscriptionServiceException;

    void deleteSubscription(Long subscriptionId);

    Subscription getSubscription(Long subscriptionId) throws SubscriptionServiceException;

    List<Subscription> getAllSubscriptions();
}
