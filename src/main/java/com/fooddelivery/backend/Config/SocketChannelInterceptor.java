package com.fooddelivery.backend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SocketChannelInterceptor implements ChannelInterceptor {
    
    @Autowired
    SocketService socketService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;
        if(accessor.getCommand().equals(StompCommand.CONNECT)) {
            // String username = accessor.getFirstNativeHeader("username");
            String token = accessor.getFirstNativeHeader("Authorization");
            System.out.println("token " + token);
            Authentication user = socketService.authenticateMessageToken( token);
            accessor.setUser(user);
        }
        return message;
    }
}
