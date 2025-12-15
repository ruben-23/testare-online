import SockJS from "sockjs-client";
import Stomp from "stompjs";

let stompClient = null;

export const connectWebSocket = (onMessageReceived) => {
    const socket = new SockJS("http://localhost:8080/ws"); // adjust if backend uses different endpoint
    stompClient = Stomp.over(socket);

    // Log all raw STOMP messages
    stompClient.debug = (str) => {
        console.log("STOMP DEBUG:", str.trim());
    };

    stompClient.connect({}, () => {
        console.log("Connected to WebSocket");
        onMessageReceived && onMessageReceived(stompClient);
    });
};

export const subscribeToActiveUsers = (username, testId, callback) => {
    if (!stompClient) {
        console.error("STOMP client not connected!");
        return;
    }
    const payload = {
        username: username,
        testId: testId
    };
    console.log("subscribing")
    // Send to the /app/subscribe endpoint
    stompClient.send("/app/subscribe", {}, JSON.stringify(payload));

    return stompClient.subscribe(`/topic/test/${testId}/activeUsers`, (message) => {

        try {
            // Parse the message
            const parsedMessage = JSON.parse(message.body);

            // Call the callback with the parsed message
            callback(parsedMessage);
        } catch (e) {
            console.error("Failed to parse message:", message.body);
        }
    });
};

export const disconnectWebSocket = () => {
    if (stompClient) {
        stompClient.disconnect(() => {
            console.log("WebSocket disconnected");
        });
    }
};
