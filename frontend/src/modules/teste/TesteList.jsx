import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

import testeService from "../../services/testeService";
import domeniiService from "../../services/domeniiService";
import userService from "../../services/userService";
import { useAuth } from "../../context/AuthContext";

import "./styles/Test.css";

const TesteList = () => {
    const { user } = useAuth();
    const navigate = useNavigate();

    const [tests, setTests] = useState([]);
    const [domenii, setDomenii] = useState([]);

    const [search, setSearch] = useState("");
    const [selectedDomeniu, setSelectedDomeniu] = useState("all");
    const [sortBy, setSortBy] = useState("data");

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
    const sorted = [...filtered].sort((a, b) =>
        new Date(a.date) - new Date(b.date)
    );

    return (
        <div>

            <div className="teste-header-row">
                <h1>Teste</h1>
                {user && user?.id === test.idUser && ( <button className="teste-btn-2">+ Creeaza Test</button> )}
                <div className="teste-controls">

                    <input
                        type="text"
                        placeholder="Search teste…"
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
                        <option value="data">Data Creării</option>
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
                            Data: <strong>{test.date}</strong>
                        </p>

                        {/* BUTTONS */}
                        <button
                            className="teste-btn"
                            onClick={() => navigate(`/teste/${test.id}/take`)}
                        >
                            Take Test
                        </button>

                        {/* AUTHOR OPTIONS */}
                        {user?.id === test.idUser && (
                            <>
                                <button className="teste-btn teste-edit">
                                    Edit
                                </button>

                                <button className="teste-btn teste-delete">
                                    Delete
                                </button>
                            </>
                        )}

                    </div>
                ))}
            </div>

        </div>
    );
};

export default TesteList;
