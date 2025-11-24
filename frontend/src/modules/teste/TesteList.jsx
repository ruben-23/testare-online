import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

import testeService from "../../services/testeService.js";
import domeniiService from "../../services/domeniiService.js";
import userService from "../../services/userService.js";

import "./styles/Test.css";

const TesteList = () => {
    const [tests, setTests] = useState([]);
    const [domenii, setDomenii] = useState([]);
    const [search, setSearch] = useState("");
    const [selectedDomeniu, setSelectedDomeniu] = useState("all");
    const [sortBy, setSortBy] = useState("data");

    // Load all data
    useEffect(() => {
        loadData();
    }, []);

    const loadData = async () => {
        try {
            const [testsRes, domeniiRes] = await Promise.all([
                testeService.getAll(),
                domeniiService.getAll()
            ]);

            // Convert domenii array → lookup map for fast access
            const domeniiMap = {};
            domeniiRes.forEach(d => {
                domeniiMap[d.id] = d.nume;
            });

            // For each test, fetch username and map domeniu name
            const testsWithNames = await Promise.all(
                testsRes.map(async (t) => {
                    // get username by id
                    const userData = await userService.getById(t.idUser);

                    return {
                        id: t.id,
                        title: t.titlu,
                        date: t.dataCrearii,
                        domeniu: domeniiMap[t.idDomeniu],
                        idDomeniu: t.idDomeniu,
                        username: userData.username
                    };
                })
            );

            setTests(testsWithNames);
            setDomenii(domeniiRes);
        } catch (e) {
            console.error("Failed loading tests or domenii", e);
        }
    };

    // FILTER BY SEARCH
    let filtered = tests.filter(test =>
        test.title.toLowerCase().includes(search.toLowerCase())
    );

    // FILTER BY DOMENIU
    if (selectedDomeniu !== "all") {
        filtered = filtered.filter(test => test.idDomeniu === Number(selectedDomeniu));
    }

    // SORT
    const sorted = [...filtered].sort((a, b) => {
        return new Date(a.date) - new Date(b.date);
    });

    return (
        <div>

            {/* HEADER ROW */}
            <div className="teste-header-row">

                <div className="teste-controls">

                    {/* SEARCH */}
                    <input
                        type="text"
                        placeholder="Search teste…"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                        className="teste-search"
                    />

                    {/* DOMENIU FILTER */}
                    <select
                        className="teste-sort"
                        value={selectedDomeniu}
                        onChange={(e) => setSelectedDomeniu(e.target.value)}
                    >
                        <option value="all">Toate Domeniile</option>
                        {domenii.map((dom) => (
                            <option key={dom.id} value={dom.id}>{dom.nume}</option>
                        ))}
                    </select>

                    {/* SORT BY DATE */}
                    <select
                        className="teste-sort"
                        value={sortBy}
                        onChange={(e) => setSortBy(e.target.value)}
                    >
                        <option value="data">Data Creării</option>
                    </select>
                </div>
            </div>

            {/* TESTE GRID */}
            <div className="teste-grid">
                {sorted.map((t) => (
                    <div className="teste-card" key={t.id}>
                        <h3>{t.title}</h3>
                        <p className="teste-small">
                            Domeniu: <strong>{t.domeniu}</strong>
                        </p>

                        <p className="teste-small">
                            Autor: <strong>{t.username}</strong>
                        </p>

                        <p className="teste-small">
                            Data: <strong>{t.date}</strong>
                        </p>

                        <Link to={`/teste/${t.id}`} className="teste-btn">
                            View Test
                        </Link>
                    </div>
                ))}
            </div>

        </div>
    );
};

export default TesteList;

