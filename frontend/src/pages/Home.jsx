import React from "react";

import { useNavigate } from "react-router-dom";
import "./styles/Home.css";

const Home = () => {
    const navigate = useNavigate();

    return (
        <div className="home-container">
            <h1 className="home-title">Aplicație de Testare Online</h1>

            <p className="home-subtitle">
                Creează, susține și gestionează teste cu ușurință.
            </p>

            <div className="home-actions">
                <button
                    className="teste-btn teste-primary"
                    onClick={() => navigate("/teste")}
                >
                    Răsfoiește teste
                </button>
            </div>
        </div>
    );
};

export default Home;
