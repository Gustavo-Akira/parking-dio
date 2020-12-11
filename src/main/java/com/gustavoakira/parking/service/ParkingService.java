package com.gustavoakira.parking.service;

import com.gustavoakira.parking.exception.ParkingNotFoundException;
import com.gustavoakira.parking.model.Parking;
import com.gustavoakira.parking.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

    private static String generateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional(readOnly = true)
    public Parking findById(String id){
        return parkingRepository.findById(id).orElseThrow(()->new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking create){
        String uuid = generateUUID();
        create.setId(uuid);
        create.setEntryDate(LocalDateTime.now());
        create = parkingRepository.save(create);
        return create;
    }

    @Transactional
    public void delete(String id){
        findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking create){
        Parking parking = findById(id);
        parking.setColor(create.getColor());
        parking.setState(create.getState());
        parking.setModel(create.getModel());
        parking.setLicense(create.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

    @Transactional
    public Parking checkOut(String id){
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);
        return parking;
    }
}
