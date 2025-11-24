import React from "react";
import { useAuth } from "../context/AuthContext";
import './styles/Profile.css'

const Profile = () => {
    const { user } = useAuth();

    if (!user) return <p>Loading...</p>;

    return (
        <div className="profile-container">
            <h1>Your Profile</h1>

            <div className="profile-card">
                <div>
                    <p>Username:</p>
                    <h3>{user.username}</h3>
                </div>

                <div>
                    <p>Role:</p>
                    <h3>{user.rol}</h3>
                </div>

                <div>
                    <p>ID:</p>
                    <h3>{user.id}</h3>
                </div>

            </div>
        </div>
    );
};

export default Profile;
