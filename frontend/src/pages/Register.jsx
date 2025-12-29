import React, { useState } from "react";
import axios from "axios";
import { useAuth } from "../context/AuthContext";
import { useNavigate, Link } from "react-router-dom";
import './styles/Auth.css';

const Register = () => {
    const { login } = useAuth();
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [parola, setParola] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");

        try {
            const response = await axios.post("http://localhost:8080/auth/register", {
                username,
                parola,
            });

            login(response.data.token);
            navigate("/");
        } catch (err) {
            if (err.response && err.response.status === 409) {
                setError("Username-ul există deja.");
            } else {
                setError("Înregistrarea a eșuat.");
            }
        }
    };

    return (
        <div className="auth-container">
            <h2>Înregistrare</h2>
            {error && <p className="auth-message error">{error}</p>}
            {success && <p className="auth-message success">{success}</p>}

            <form className="auth-form" onSubmit={handleSubmit}>
                <label>Username</label>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />

                <label>Parolă</label>
                <input
                    type="password"
                    value={parola}
                    onChange={(e) => setParola(e.target.value)}
                    required
                />

                <button className="auth-btn" type="submit">Creează cont</button>
            </form>

            <div className="auth-footer">
                Ai deja cont? <Link to="/login">Autentificare</Link>
            </div>
        </div>
    );
};

export default Register;
