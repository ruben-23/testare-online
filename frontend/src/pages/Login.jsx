import React, { useState } from "react";
import axios from "axios";
import { useAuth } from "../context/AuthContext";
import { useNavigate, Link } from "react-router-dom";
import './styles/Auth.css';

const Login = () => {
    const { login } = useAuth();
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [parola, setParola] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await axios.post("http://localhost:8080/auth/login", {
                username,
                parola,
            });

            login(response.data.token);
            navigate("/");
        } catch (err) {
            setError("Date de autentificare invalide");
        }
    };

    return (
        <div className="auth-container">
            <h2>Autentificare</h2>
            {error && <p className="auth-message error">{error}</p>}

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

                <button className="auth-btn" type="submit">Autentificare</button>
            </form>

            <div className="auth-footer">
                Nu ai cont? <Link to="/register">Înregistrează-te</Link>
            </div>
        </div>
    );
};

export default Login;
