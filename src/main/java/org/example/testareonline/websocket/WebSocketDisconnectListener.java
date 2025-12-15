package org.example.testareonline.websocket;

import lombok.AllArgsConstructor;
import org.example.testareonline.service.ActiveUsersService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@AllArgsConstructor
public class WebSocketDisconnectListener {

    private final ActiveUsersService activeUsersService;
    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());

        Object usernameObj = headers.getSessionAttributes().get("username");
        Object testIdObj = headers.getSessionAttributes().get("testId");

        if (usernameObj instanceof String username && testIdObj instanceof Integer testId) {

            // delete username from the active users list
            activeUsersService.removeUserFromTest(testId, username);
            // send updated list
            messagingTemplate.convertAndSend("/topic/test/" + testId + "/activeUsers", activeUsersService.getActiveUsersForTest(testId));
        } else {
            System.out.println("Disconnect event with missing or invalid attributes: username=" + usernameObj + "testId=" + testIdObj);
        }
    }
}