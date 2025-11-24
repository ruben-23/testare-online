import React from "react";
import { Outlet } from "react-router-dom";
import "./styles/Test.css";

const TesteLayout = () => {
    return (
        <div className="teste-layout">
            <h1>Teste</h1>
            <Outlet />
        </div>
    );
};

export default TesteLayout;
