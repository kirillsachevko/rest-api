package org.javadv.controller;

import org.javadv.domain.Subscription;
import org.javadv.dto.SubscriptionRequestDto;
import org.javadv.dto.SubscriptionResponseDto;
import org.javadv.impl.exception.SubscriptionServiceException;
import org.javadv.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscription")
public class ServiceController {
    private final SubscriptionService subscriptionService;
    private final ModelMapper mapper;

    public ServiceController(SubscriptionService subscriptionService, ModelMapper mapper) {
        this.subscriptionService = subscriptionService;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return convertToDto(subscriptionService.createSubscription(convertToModel(subscriptionRequestDto)));
    }

    @PutMapping("/update")
    public SubscriptionResponseDto updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) throws SubscriptionServiceException {
        return convertToDto(subscriptionService.updateSubscription(convertToModel(subscriptionRequestDto)));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
    }

    @GetMapping("/get")
    public SubscriptionResponseDto getSubscription(@RequestParam Long subscriptionId) throws SubscriptionServiceException {
        return convertToDto(subscriptionService.getSubscription(subscriptionId));
    }

    @GetMapping("/getAll")
    public List<SubscriptionResponseDto> getAllSubscription() {
        return subscriptionService.getAllSubscriptions().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Subscription convertToModel(SubscriptionRequestDto dto) {
        return mapper.map(dto, Subscription.class);
    }

    private SubscriptionResponseDto convertToDto(Subscription subscription) {
        return mapper.map(subscription, SubscriptionResponseDto.class);
    }
}
