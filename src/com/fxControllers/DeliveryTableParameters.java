package com.fxControllers;

import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class DeliveryTableParameters {
    private SimpleStringProperty deliveryId = new SimpleStringProperty();
    private SimpleStringProperty deliveryName = new SimpleStringProperty();
    private SimpleStringProperty deliveryType = new SimpleStringProperty();
    private SimpleStringProperty deliverySize = new SimpleStringProperty();
    private SimpleStringProperty deliveryStatus= new SimpleStringProperty();
    private SimpleStringProperty deliveryClient = new SimpleStringProperty();
    private SimpleStringProperty deliveryDriver = new SimpleStringProperty();
    private SimpleStringProperty deliveryCrtDate = new SimpleStringProperty();
    private SimpleStringProperty deliveryDeadline = new SimpleStringProperty();

    public String getDeliveryId() {
        return deliveryId.get();
    }

    public SimpleStringProperty deliveryIdProperty() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId.set(deliveryId);
    }

    public String getDeliveryName() {
        return deliveryName.get();
    }

    public SimpleStringProperty deliveryNameProperty() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName.set(deliveryName);
    }

    public String getDeliveryType() {
        return deliveryType.get();
    }

    public SimpleStringProperty deliveryTypeProperty() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType.set(deliveryType);
    }

    public String getDeliverySize() {
        return deliverySize.get();
    }

    public SimpleStringProperty deliverySizeProperty() {
        return deliverySize;
    }

    public void setDeliverySize(String deliverySize) {
        this.deliverySize.set(deliverySize);
    }

    public String getDeliveryStatus() {
        return deliveryStatus.get();
    }

    public SimpleStringProperty deliveryStatusProperty() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus.set(deliveryStatus);
    }

    public String getDeliveryClient() {
        return deliveryClient.get();
    }

    public SimpleStringProperty deliveryClientProperty() {
        return deliveryClient;
    }

    public void setDeliveryClient(String deliveryClient) {
        this.deliveryClient.set(deliveryClient);
    }

    public String getDeliveryDriver() {
        return deliveryDriver.get();
    }

    public SimpleStringProperty deliveryDriverProperty() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(String deliveryDriver) {
        this.deliveryDriver.set(deliveryDriver);
    }

    public String getDeliveryCrtDate() {
        return deliveryCrtDate.get();
    }

    public SimpleStringProperty deliveryCrtDateProperty() {
        return deliveryCrtDate;
    }

    public void setDeliveryCrtDate(String deliveryCrtDate) {
        this.deliveryCrtDate.set(deliveryCrtDate);
    }

    public String getDeliveryDeadline() {
        return deliveryDeadline.get();
    }

    public SimpleStringProperty deliveryDeadlineProperty() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(String deliveryDeadline) {
        this.deliveryDeadline.set(deliveryDeadline);
    }
}

