import React from "react";
import { useLocation, useParams } from "react-router-dom";

const TestResult = () => {
    const { state } = useLocation();
    const { id } = useParams();

    if (!state?.result) {
        return <p>No result data. Please complete the test first.</p>;
    }

    const { result, testTitle } = state;

    return (
        <div className="take-test-container">
            <h2>Test Completed!</h2>
            <h3>{testTitle}</h3>

            <div style={{ margin: "30px 0", padding: "20px", background: "#f0f8ff", borderRadius: "8px" }}>
                <h2>Your Score: {result.score} / {result.totalScore}</h2>
                <p style={{ fontSize: "18px" }}>
                    Percentage: {result.percentage ? result.percentage.toFixed(1) : "N/A"}%
                </p>
                {result.passed !== undefined && (
                    <p style={{ color: result.passed ? "green" : "red", fontWeight: "bold" }}>
                        {result.passed ? "Congratulations! You passed!" : "You did not pass. Try again!"}
                    </p>
                )}
            </div>

            <button className="teste-btn" onClick={() => window.history.back()}>
                Back to Tests
            </button>
        </div>
    );
};

export default TestResult;