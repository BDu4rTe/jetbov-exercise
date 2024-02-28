package com.jetbov.exercice.infra;

import com.jetbov.exercice.adapters.AreaGateway;
import com.jetbov.exercice.adapters.OxGateway;
import com.jetbov.exercice.application.AreaServiceImpl;
import com.jetbov.exercice.application.OxServiceImpl;
import com.jetbov.exercice.core.services.AreaService;
import com.jetbov.exercice.core.services.OxService;
import com.jetbov.exercice.infra.gateways.AreaRepositoryGateway;
import com.jetbov.exercice.infra.gateways.OxRepositoryGateway;
import com.jetbov.exercice.infra.repositories.AreaRepository;
import com.jetbov.exercice.infra.repositories.OxRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {

    @Bean
    AreaGateway areaGateway(AreaRepository repository, GatewayHelper helper) {
        return new AreaRepositoryGateway(repository, helper);
    }

    @Bean
    AreaService areaService(AreaGateway gateway) {
        return new AreaServiceImpl(gateway);
    }

    @Bean
    OxGateway oxGateway(OxRepository repository, GatewayHelper helper) {
        return new OxRepositoryGateway(repository, helper);
    }

    @Bean
    OxService oxService(OxGateway gateway) {
        return new OxServiceImpl(gateway);
    }
}
