package com.finalassignment.assignment.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

@PropertySource("classpath:messages.properties")
@Data
public class AbstractMessage {
    @Autowired
    private MessageSource messageSource;

    public String getItemFound(String itemFound) {
        return messageSource.getMessage(itemFound, null, Locale.ENGLISH);
    }

    public String getItemNotFound(String itemNotFound) {
        return messageSource.getMessage(itemNotFound, null, Locale.ENGLISH);
    }

    public String getItemNotFoundById(String itemNotFoundById) {
        return messageSource.getMessage(itemNotFoundById, null, Locale.ENGLISH);
    }

    public String getItemUpdated(String itemUpdated) {
        return messageSource.getMessage(itemUpdated, null, Locale.ENGLISH);
    }

    public String getItemDeleted(String itemDeleted) {
        return messageSource.getMessage(itemDeleted, null, Locale.ENGLISH);
    }

    public String getItemAdded(String itemAdded) {
        return messageSource.getMessage(itemAdded, null, Locale.ENGLISH);
    }

    public String getCustomerNotFound(String customerNotFound) {
        return messageSource.getMessage(customerNotFound, null, Locale.ENGLISH);
    }

    public String getCartFound(String cartFound) {
        return messageSource.getMessage(cartFound, null, Locale.ENGLISH);
    }

    public String getCartNotFoundById(String cartNotFoundById) {
        return messageSource.getMessage(cartNotFoundById, null, Locale.ENGLISH);
    }

    public String getCartAdded(String cartAdded) {
        return messageSource.getMessage(cartAdded, null, Locale.ENGLISH);
    }

    public String getOrderFound(String orderFound) {
        return messageSource.getMessage(orderFound, null, Locale.ENGLISH);
    }

    public String getOrderAdded(String orderAdded) {
        return messageSource.getMessage(orderAdded, null, Locale.ENGLISH);
    }

    public String getCartDetailNotFound(String cartDetailNotFound) {
        return messageSource.getMessage(cartDetailNotFound, null, Locale.ENGLISH);
    }
}
