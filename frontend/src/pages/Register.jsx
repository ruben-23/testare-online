import React, { useState } from "react";
import axios from "axios";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

const Register = () => {
    const { login } = useAuth();   // reuse login() to store the JWT
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [parola, setParola] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/auth/register", {
                username,
                parola,
            });

            const token = response.data.token;

            login(token);       // Save JWT & mark user as logged in
            navigate("/");      // Redirect to home after registering

        } catch (err) {
            if (err.response && err.response.status === 409) {
                setError("Username already exists.");
            } else {
                setError("Registration failed.");
            }
        }
    };

    return (
        <div className="auth-container">
            <h2>Register</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}
            {success && <p style={{ color: "green" }}>{success}</p>}

            <form onSubmit={handleSubmit}>
                <label>Username</label>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />

                <label>Parola</label>
                <input
                    type="password"
                    value={parola}
                    onChange={(e) => setParola(e.target.value)}
                    required
                />

                <button type="submit">Create Account</button>
            </form>
        </div>
    );
};

export default Register;
