package org.javadv.impl;

import org.javadv.domain.Subscription;
import org.javadv.impl.exception.SubscriptionServiceException;
import org.javadv.persictance.SubscriptionRepository;
import org.javadv.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) throws SubscriptionServiceException {
        Long subId = subscription.getId();
        Optional<Subscription> subscriptionOptional =
                subscriptionRepository.findById(subId);
        if (subscriptionOptional.isPresent()) {
            return subscriptionOptional.get();
        } else {
            throw new SubscriptionServiceException("Subscription with id: " + subId + " does not exist");
        }
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public Subscription getSubscription(Long subscriptionId) throws SubscriptionServiceException {
        Optional<Subscription> subscriptionOptional =
                subscriptionRepository.findById(subscriptionId);
        if (subscriptionOptional.isPresent()) {
            return subscriptionOptional.get();
        } else {
            throw new SubscriptionServiceException("Subscription with id: " + subscriptionId + " does not exist");
        }
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionRepository.findAll().forEach(subscriptionList::add);
        return subscriptionList;
    }
}
