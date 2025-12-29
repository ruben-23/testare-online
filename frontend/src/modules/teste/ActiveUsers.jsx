import React, { useEffect, useState } from "react";
import "./styles/ActiveUsers.css";
import {
    connectWebSocket,
    subscribeToActiveUsers,
    disconnectWebSocket,
} from "../../services/websocketService.js";
import {useAuth} from "../../context/AuthContext.jsx";

const ActiveUsers = ({ testId, guestUsername }) => {
    const { user } = useAuth();

    const [users, setUsers] = useState([]);

    useEffect(() => {
        let subscription = null;

        const username = user !== null ? user.username : guestUsername;

        // Connect WebSocket
        connectWebSocket(async () => {
            console.log("connecting to websocket")
            // Once connected, subscribe to active users
            subscription = subscribeToActiveUsers(username, testId, (updatedUsers) => {
                setUsers(updatedUsers);
                console.log("USERS", updatedUsers);

            });

        });
        // Cleanup function
        return () => {
            if (subscription) {
                subscription.unsubscribe();
            }
            disconnectWebSocket();
        };
    }, [testId, guestUsername]);

    return (
        <div className="active-users-wrapper">
            <h3 className="active-users-title">Utilizatori care susțin acest test</h3>
            <div className="active-users-list">
                {users.length === 0 ? (
                    <p>Niciun utilizator activ...</p>
                ) : (
                    users.map((username, index) => (
                        <div key={index} className="active-user-item">
                            {username}
                        </div>
                    ))
                )}
            </div>
        </div>
    );
};

export default ActiveUsers;