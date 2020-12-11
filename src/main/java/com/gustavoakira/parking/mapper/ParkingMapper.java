package com.gustavoakira.parking.mapper;

import com.gustavoakira.parking.controller.dto.ParkingCreateDTO;
import com.gustavoakira.parking.controller.dto.ParkingDTO;
import com.gustavoakira.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public ParkingDTO toParkingDTO(Parking parking){
        return  MODEL_MAPPER.map(parking,ParkingDTO.class);
    }
    public List<ParkingDTO> toParkingDTOList(List<Parking> parkings){
        return parkings.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }
    public Parking toParking(ParkingDTO dto){
        return MODEL_MAPPER.map(dto,Parking.class);
    }
    public List<Parking> toParkingList(List<ParkingDTO> parkingDTOS){
        return parkingDTOS.stream().map(this::toParking).collect(Collectors.toList());
    }
    public Parking createToParking(ParkingCreateDTO dto){
        return MODEL_MAPPER.map(dto,Parking.class);
    }
}
