package br.senai.sc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.Getter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@Getter
public class ServiceConfig {

}
