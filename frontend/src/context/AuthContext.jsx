import { createContext, useState, useContext, useEffect } from "react";
import api from "../api/api";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem("token") || null);
    const [user, setUser] = useState(null);
    const isLoggedIn = !!token;

    // Load user data using /auth/me
    const loadUser = async () => {
        if (!token) return;

        try {
            const response = await api.get("/auth/me");
            setUser(response.data);        // { id, username, rol }
        } catch (err) {
            console.error("Failed to fetch user:", err);
            logout();                      // invalid token → logout
        }
    };

    useEffect(() => {
        loadUser();
    }, [token]);

    const login = (jwt) => {
        localStorage.setItem("token", jwt);
        setToken(jwt);
    };

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, token, isLoggedIn, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
