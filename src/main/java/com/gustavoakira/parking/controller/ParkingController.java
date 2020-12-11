package com.gustavoakira.parking.controller;

import com.gustavoakira.parking.controller.dto.ParkingCreateDTO;
import com.gustavoakira.parking.controller.dto.ParkingDTO;
import com.gustavoakira.parking.mapper.ParkingMapper;
import com.gustavoakira.parking.model.Parking;
import com.gustavoakira.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ParkingController {

    private final ParkingService service;
    private final ParkingMapper mapper;
    @GetMapping("parkings")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkings = service.findAll();
        List<ParkingDTO> result = mapper.toParkingDTOList(parkings);
        return ResponseEntity.ok(result);
    }
    @GetMapping("parking/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(mapper.toParkingDTO(service.findById(id)));
    }

    @PostMapping("parking")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        Parking parking = mapper.createToParking(dto);
        parking = service.create(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toParkingDTO(parking));
    }

    @PutMapping("parking/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
        Parking updatedParking = mapper.createToParking(dto);
        Parking parking = service.update(id,updatedParking);
        return ResponseEntity.ok(mapper.toParkingDTO(parking));
    }

    @PostMapping("parking/{id}")
    public ResponseEntity<ParkingDTO> checkout(@PathVariable String id){
        Parking parking = service.checkOut(id);
        return ResponseEntity.ok(mapper.toParkingDTO(parking));
    }
}
