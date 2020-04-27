package org.SpringConfigClient;

import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class RefreshContextConfig {

    private RefreshEndpoint refreshEndpoint;

    public RefreshContextConfig(RefreshEndpoint refreshEndpoint) {
        this.refreshEndpoint = refreshEndpoint;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void refreshContextPeriodically() {
        refreshEndpoint.refresh();
    }
}
