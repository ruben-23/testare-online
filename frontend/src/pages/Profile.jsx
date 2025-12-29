import React from "react";
import { useAuth } from "../context/AuthContext";
import './styles/Profile.css';

const Profile = () => {
    const { user } = useAuth();

    if (!user) return <p>Loading...</p>;

    return (
        <div className="profile-container">
            <h1 className="profile-title">Profilul tău</h1>

            <div className="profile-card">
                <div className="profile-item">
                    <p className="profile-label">Username</p>
                    <h3 className="profile-value">{user.username}</h3>
                </div>

                <div className="profile-item">
                    <p className="profile-label">Rol</p>
                    <h3 className="profile-value">{user.rol}</h3>
                </div>

                <div className="profile-item">
                    <p className="profile-label">ID</p>
                    <h3 className="profile-value">{user.id}</h3>
                </div>
            </div>
        </div>
    );
};

export default Profile;
