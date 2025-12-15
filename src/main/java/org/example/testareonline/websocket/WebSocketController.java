package org.example.testareonline.websocket;

import lombok.AllArgsConstructor;
import org.example.testareonline.dto.TakeTestUserInfo;
import org.example.testareonline.service.ActiveUsersService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class WebSocketController {
    private final ActiveUsersService activeUsersService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * This method is triggered when a user subscribes to a WebSocket endpoint.
     * It handles the user joining a specific test and updates the active users list.
     */
    @MessageMapping("/subscribe")
    public void subscribe(@Payload TakeTestUserInfo userInfo, SimpMessageHeaderAccessor headerAccessor) {
        String username = userInfo.getUsername();
        Integer testId = userInfo.getTestId();

        // Store the username and testId in the session attributes (for disconnect handling)
        headerAccessor.getSessionAttributes().put("username", username);
        headerAccessor.getSessionAttributes().put("testId", testId);

        // Add the user to the active users list for the specified test and send the list
        activeUsersService.addUserToTest(testId, username);
        messagingTemplate.convertAndSend("/topic/test/" + testId + "/activeUsers", activeUsersService.getActiveUsersForTest(testId));

    }
}

