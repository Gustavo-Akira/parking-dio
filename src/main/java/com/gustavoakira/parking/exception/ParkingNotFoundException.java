package com.gustavoakira.parking.exception;

public class ParkingNotFoundException extends RuntimeException{
    public ParkingNotFoundException(String id){
        super("Park not found with id: "+id);
    }
}
