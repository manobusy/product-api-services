package com.product.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;

/**
 * @author Manohar Perumal
 * 
 *This is Monitoring Configuration - prometheus
 *
 */
@Configuration
class MonitoringConfig {

	@Bean
	public CollectorRegistry collectorRegistry() {
	    return CollectorRegistry.defaultRegistry;
	}

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        DefaultExports.initialize();
        return new ServletRegistrationBean(new MetricsServlet(), "/prometheus");
    }
}
