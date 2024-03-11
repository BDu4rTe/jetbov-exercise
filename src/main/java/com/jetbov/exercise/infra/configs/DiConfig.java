package com.jetbov.exercise.infra.configs;

import com.jetbov.exercise.application.AreaServiceImpl;
import com.jetbov.exercise.application.MoveServiceImpl;
import com.jetbov.exercise.application.OxServiceImpl;
import com.jetbov.exercise.core.services.AreaService;
import com.jetbov.exercise.core.services.MoveService;
import com.jetbov.exercise.core.services.OxService;
import com.jetbov.exercise.infra.repositories.AreaRepository;
import com.jetbov.exercise.infra.repositories.MoveRepository;
import com.jetbov.exercise.infra.repositories.OxRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {


    @Bean
    AreaService areaService(AreaRepository repository) {
        return new AreaServiceImpl(repository);
    }

    @Bean
    OxService oxService(OxRepository oxRepository) {
        return new OxServiceImpl(oxRepository);
    }

    @Bean
    MoveService moveService(MoveRepository moveRepository, OxRepository oxRepository, AreaRepository areaRepository) {
        return new MoveServiceImpl(moveRepository, oxRepository, areaRepository);
    }
}
