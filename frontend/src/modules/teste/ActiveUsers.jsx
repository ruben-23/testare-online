import React from "react";
import "./styles/ActiveUsers.css";

const mockUsers = [
    { id: 1, username: "alex" },
    { id: 2, username: "maria" },
    { id: 3, username: "ionut" },
    { id: 4, username: "george" },
    { id: 5, username: "andreea" },
    { id: 6, username: "larisa" },
    { id: 7, username: "cristi" },
];

const ActiveUsers = () => {
    return (
        <div className="active-users-wrapper">
            <h3 className="active-users-title">Active Users</h3>

            <div className="active-users-list">
                {mockUsers.map((user) => (
                    <div key={user.id} className="active-user-item">
                        {user.username}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ActiveUsers;
