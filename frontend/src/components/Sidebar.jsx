import React from "react";
import "./styles/Sidebar.css";

const Sidebar = () => {
    return (
        <aside className="sidebar">
            <ul>
                <li>Dashboard</li>
                <li>Profile</li>
                <li>Settings</li>
            </ul>
        </aside>
    );
};

export default Sidebar;
