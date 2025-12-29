import React from "react";
import { Link } from "react-router-dom";
import "./styles/Navbar.css";
import {useAuth} from "../context/AuthContext.jsx";

const Navbar = () => {
    const {isLoggedIn } = useAuth();
    return (
        <nav className="navbar">
            <h2 className="logo">TestApp</h2>
            <div className="nav-links">
                <Link to="/">Acasă</Link>
                <Link to="/teste">Teste</Link>
            </div>
            <div className="nav-links">

                {!isLoggedIn && <>
                    <Link to="/login">Autentificare</Link>
                    <Link to="/register">Înregistrare</Link>
                </>}
                {isLoggedIn && <>
                    <Link to="/profile">Profil</Link>
                    <Link to="/logout">Deconectare</Link>
                </>}
            </div>

        </nav>
    );
};

export default Navbar;
