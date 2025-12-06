import React from "react";
import { Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import "./App.css";
import Test from "./modules/teste/Test.jsx";
import TesteList from './modules/teste/TesteList.jsx'
import TesteLayout from './modules/teste/TesteLayout.jsx'
import Logout from "./pages/Logout.jsx";
import Profile from "./pages/Profile";
import ProtectedRoute from "./components/ProtectedRoutes.jsx";
import TakeTest from "./modules/teste/TakeTest.jsx";
import TestResult from "./modules/teste/TestResult.jsx";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Layout />}>
                <Route index element={<Home />} />
                <Route path="login" element={<Login />} />
                <Route path="register" element={<Register />} />

                <Route path="logout" element={
                    <ProtectedRoute>
                        <Logout />
                    </ProtectedRoute>}
                />

                <Route path="profile" element={
                    <ProtectedRoute>
                        <Profile />
                    </ProtectedRoute>}
                />

                <Route path="teste" element={<TesteLayout />}>
                    <Route index element={<TesteList />} />
                    <Route path=":id" element={<Test />} />
                    <Route path=":id/take" element={<TakeTest />} />
                    <Route path=":id/result" element={<TestResult />} />
                </Route>
            </Route>
        </Routes>
    );
}

export default App;
