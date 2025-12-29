import React from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";

const TestResult = () => {
    const { state } = useLocation();
    const { id } = useParams();
    const navigate  = useNavigate();

    if (!state?.result) {
        return <p>Nu există date despre rezultat. Te rugăm să completezi mai întâi testul.</p>;
    }

    const { result, testTitle } = state;
    console.log("-- [RESULT] -- :" + result);

    return (
        <div className="take-test-container">
            <h2>Testul a fost finalizat!</h2>
            <h3>{testTitle}</h3>

            <div style={{ margin: "30px 0", padding: "20px", background: "#f0f8ff", borderRadius: "8px" }}>
                <h2>Scorul tău: {result.score} / {result.totalScore}</h2>
                <p style={{ fontSize: "18px" }}>
                    Procentaj: {result.percentage.toFixed(1)}%
                </p>
                {result.passed !== undefined && (
                    <p style={{ color: result.passed ? "green" : "red", fontWeight: "bold" }}>
                        {result.passed ? "Felicitări! Ai promovat!" : "Nu ai promovat. Încearcă din nou!"}
                    </p>
                )}
            </div>

            <div className="result-actions">
                <button
                    className="teste-btn teste-primary"
                    onClick={() => window.history.back()}
                >
                    Susține din nou
                </button>

                <button
                    className="teste-btn teste-secondary"
                    onClick={() => navigate(`/teste`)}
                >
                    Înapoi la Teste
                </button>
            </div>
        </div>
    );
};

export default TestResult;
