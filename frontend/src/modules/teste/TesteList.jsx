import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import testeService from "../../services/testeService";
import domeniiService from "../../services/domeniiService";
import userService from "../../services/userService";
import { useAuth } from "../../context/AuthContext";
import CreateTest from "./CreateTest";

import "./styles/Test.css";

const TesteList = () => {
    const { user } = useAuth();
    const navigate = useNavigate();

    const [tests, setTests] = useState([]);
    const [domenii, setDomenii] = useState([]);
    const [showCreateTest, setShowCreateTest] = useState(false);
    const [editingTestId, setEditingTestId] = useState(null);

    const [search, setSearch] = useState("");
    const [selectedDomeniu, setSelectedDomeniu] = useState("all");
    const [sortBy, setSortBy] = useState("newest");



    useEffect(() => {
        loadData();
    }, []);

    const loadData = async () => {
        try {
            const [testsRes, domeniiRes] = await Promise.all([
                testeService.getAll(),
                domeniiService.getAll()
            ]);

            const domeniiMap = {};
            domeniiRes.forEach(d => (domeniiMap[d.id] = d.nume));

            const testsWithNames = await Promise.all(
                testsRes.map(async (t) => {
                    const author = await userService.getById(t.idUser);
                    return {
                        id: t.id,
                        title: t.titlu,
                        date: t.dataCrearii,
                        domeniu: domeniiMap[t.idDomeniu],
                        idDomeniu: t.idDomeniu,
                        idUser: t.idUser,
                        username: author.username
                    };
                })
            );

            setTests(testsWithNames);
            setDomenii(domeniiRes);

        } catch (err) {
            console.error(err);
        }
    };

    const handleOpenCreateTest = () => {
        setEditingTestId(null);
        setShowCreateTest(true);
    };

    const handleOpenEditTest = (testId) => {
        setEditingTestId(testId);
        setShowCreateTest(true);
    };

    const handleDeleteTest = async (testId) => {
        if (window.confirm("Ești sigur că vrei să ștergi acest test?")) {
            try {
                await testeService.delete(testId);
                alert("Testul a fost șters cu succes!");
                loadData();
            } catch (err) {
                console.error("Failed to delete test:", err);
                alert("Ștergerea testului a eșuat");
            }
        }
    };

    const handleTestSaved = () => {
        setShowCreateTest(false);
        setEditingTestId(null);
        loadData();
    };

    const formatDate = (date) =>
        new Date(date).toLocaleDateString();

    // SEARCH
    let filtered = tests.filter(t =>
        t.title.toLowerCase().includes(search.toLowerCase())
    );

    // FILTER BY DOMENIU
    if (selectedDomeniu !== "all") {
        filtered = filtered.filter(t =>
            t.idDomeniu === Number(selectedDomeniu)
        );
    }

    // SORT
    const sorted = [...filtered].sort((a, b) => {
        const dateA = new Date(a.date);
        const dateB = new Date(b.date);

        if (sortBy === "newest") {
            return dateB - dateA; // newest first
        }

        if (sortBy === "oldest") {
            return dateA - dateB; // oldest first
        }

        return 0;
    });

    return (
        <div>
            <div className="teste-header-row">
                <h1>Teste</h1>
                {user && (
                    <button
                        className="teste-btn-2"
                        onClick={handleOpenCreateTest}
                    >
                        + Creeaza Test
                    </button>
                )}
                <div className="teste-controls">
                    <input
                        type="text"
                        placeholder="Caută teste…"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                        className="teste-search"
                    />

                    <select
                        className="teste-sort"
                        value={selectedDomeniu}
                        onChange={(e) => setSelectedDomeniu(e.target.value)}
                    >
                        <option value="all">Toate Domeniile</option>
                        {domenii.map(dom => (
                            <option key={dom.id} value={dom.id}>
                                {dom.nume}
                            </option>
                        ))}
                    </select>

                    <select
                        className="teste-sort"
                        value={sortBy}
                        onChange={(e) => setSortBy(e.target.value)}
                    >
                        <option value="newest">Cele mai noi</option>
                        <option value="oldest">Cele mai vechi</option>
                    </select>
                </div>
            </div>

            {/* TEST CARDS */}
            <div className="teste-grid">
                {sorted.map(test => (
                    <div className="teste-card" key={test.id}>
                        <h3>{test.title}</h3>
                        <p className="teste-small">
                            Domeniu: <strong>{test.domeniu}</strong>
                        </p>
                        <p className="teste-small">
                            Autor: <strong>{test.username}</strong>
                        </p>
                        <p className="teste-small">
                            Data: <strong>{formatDate(test.date)}</strong>
                        </p>
                        <p className="teste-small">
                            Ora: <strong>{test.date.split("T")[1]}</strong>
                        </p>

                        <div className="teste-card-actions">
                            <button
                                className="teste-btn teste-primary"
                                onClick={() => navigate(`/teste/${test.id}/take`)}
                            >
                                Susține Testul
                            </button>

                            {user?.id === test.idUser && (
                                <>
                                    <button
                                        className="teste-btn teste-secondary"
                                        onClick={() => handleOpenEditTest(test.id)}
                                    >
                                        Editează
                                    </button>

                                    <button
                                        className="teste-btn teste-danger"
                                        onClick={() => handleDeleteTest(test.id)}
                                    >
                                        Șterge
                                    </button>
                                </>
                            )}
                        </div>

                    </div>
                ))}
            </div>

            {/* CREATE/EDIT TEST MODAL */}
            {showCreateTest && (
                <CreateTest
                    testId={editingTestId}
                    onClose={handleTestSaved}
                />
            )}
        </div>
    );
};

export default TesteList;