package model;

import java.util.Date;

public class Rental {
    private int rentalId;
    private Date rentDate;
    private Boat boat;
    private Customer customer;
    private String startTime;
    private String endTime;
    private double rentDuration;
    private double totalPrice;
    private boolean paymentIsDone;

    public Rental(int rentalId, Date rentDate, Boat boat, Customer customer, String startTime, String endTime,
                  double rentDuration, boolean paymentIsDone) {

        this.rentalId = rentalId;
        this.rentDate = rentDate;
        this.boat = boat;
        this.customer = customer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rentDuration = rentDuration;
        this.totalPrice = calculateTotalPrice(boat.getMinimumPrice());
        this.paymentIsDone = paymentIsDone;
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

    public double getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(double rentDuration) {
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

    public double calculateTotalPrice(double minimumPrice){
        return totalPrice = rentDuration * minimumPrice;
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
                '}';
    }
}
