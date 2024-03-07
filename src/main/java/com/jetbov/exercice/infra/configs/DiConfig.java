package com.jetbov.exercice.infra.configs;

import com.jetbov.exercice.application.AreaServiceImpl;
import com.jetbov.exercice.application.OxServiceImpl;
import com.jetbov.exercice.core.services.AreaService;
import com.jetbov.exercice.core.services.OxService;
import com.jetbov.exercice.infra.repositories.AreaRepository;
import com.jetbov.exercice.infra.repositories.MoveRepository;
import com.jetbov.exercice.infra.repositories.OxRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {


    @Bean
    AreaService areaService(AreaRepository repository) {
        return new AreaServiceImpl(repository);
    }

    @Bean
    OxService oxService(OxRepository oxRepository, AreaRepository areaRepository, MoveRepository moveRepository) {
        return new OxServiceImpl(oxRepository, areaRepository, moveRepository);
    }
}
