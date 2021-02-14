package me.john.amiscaray.springwebsocketdemo.config;

import me.john.amiscaray.springwebsocketdemo.service.AuthChannelInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private AuthChannelInterceptor channelInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // Set prefixes for the endpoint that the client listens for our messages from
        registry.enableSimpleBroker("/topic");
        // Set prefix for endpoints the client will send messages to
        registry.setApplicationDestinationPrefixes("/app");

    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // Registers the endpoint where the handshake will take place
        registry.addEndpoint("/stomp")
                // Allow the origin http://localhost:63343 to send messages to us. (Base url of the client)
                .setAllowedOrigins("http://localhost:63343")
                // Enable SockJS fallback options
                .withSockJS();

    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

        // Add our interceptor for authentication/authorization
        registration.interceptors(channelInterceptor);

    }
}
