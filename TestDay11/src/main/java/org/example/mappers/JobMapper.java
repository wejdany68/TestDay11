package org.example.mappers;

import org.example.dto.JobDTO;
import org.example.models.Jobs;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper
{
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    JobDTO receiveModel(Jobs jobs);

    Jobs receiveJobDTO(JobDTO jobDTO);
}
