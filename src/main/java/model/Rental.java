package model;

import java.util.Date;

public class Rental {
    private int rentalId;
    private Date rentDate;
    private Boat boat;
    private Customer customer;
    private String startTime;
    private String endTime;
    private int rentDuration;
    private double totalPrice;
    private boolean paymentIsDone;
    private boolean isReturned;

    public Rental(int rentalId, Date rentDate, Boat boat, Customer customer, String startTime, String endTime, int rentDuration, double totalPrice, boolean paymentIsDone, boolean isReturned) {

        this.rentalId = rentalId;
        this.rentDate = rentDate;
        this.boat = boat;
        this.customer = customer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rentDuration = rentDuration;
        this.totalPrice = totalPrice;
        this.paymentIsDone = paymentIsDone;
        this.isReturned = isReturned;
    }

    public Rental() {

    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(int rentDuration) {
        this.rentDuration = rentDuration;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaymentIsDone() {
        return paymentIsDone;
    }

    public void setPaymentIsDone(boolean paymentIsDone) {
        this.paymentIsDone = paymentIsDone;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", rentDate=" + rentDate +
                ", boat=" + boat +
                ", customer=" + customer +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rentDuration=" + rentDuration +
                ", totalPrice=" + totalPrice +
                ", paymentIsDone=" + paymentIsDone +
                ", isReturned=" + isReturned +
                '}';
    }
}
