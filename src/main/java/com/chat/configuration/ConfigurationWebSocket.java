package com.chat.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ConfigurationWebSocket implements WebSocketMessageBrokerConfigurer{


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //endpoint para o usuário se conectar ao socket
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registrar os endpoints
        registry.addEndpoint("chat-socket")
        .setAllowedOrigins("http://localhost:4200")
        .withSockJS();

    }
}
