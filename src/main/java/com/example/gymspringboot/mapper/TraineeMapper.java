package com.example.gymspringboot.mapper;

import com.example.gymspringboot.domain.Trainee;
import com.example.gymspringboot.dto.request.TraineeRegistrationRequest;
import com.example.gymspringboot.dto.response.RegistrationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TraineeMapper {
    TraineeMapper INSTANCE = Mappers.getMapper(TraineeMapper.class);

    Trainee toModel(TraineeRegistrationRequest traineeRegistrationRequest);

    RegistrationResponse toDTO(Trainee trainee);

}
