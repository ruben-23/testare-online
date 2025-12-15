import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import testeService from "../../services/testeService";
import "./styles/Test.css";
import ActiveUsers from "./ActiveUsers.jsx";
import {useAuth} from "../../context/AuthContext.jsx";

const TakeTest = () => {
    const { user } = useAuth();
    const { id } = useParams();
    const navigate = useNavigate();

    const [test, setTest] = useState(null);
    const [answers, setAnswers] = useState({});
    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [guestUsername, setGuestUsername] = useState(null); // for guest users

    useEffect(() => {
        loadTest();

        // Cleanup function to clear guest username when the component unmounts
        return () => {
            if (guestUsername) {
                localStorage.removeItem(`guestUsername_test_${id}`);
            }
        };
    }, [id]);

    const loadTest = async () => {
        try {
            const data = await testeService.getFull(id);
            setTest(data);
            if (data.guestUsername) {
                setGuestUsername(data.guestUsername);
                localStorage.setItem(`guestUsername_test_${id}`, data.guestUsername);
            }
        } catch (e) {
            console.error("Error loading test", e);
        }
    };

    const handleCheckboxChange = (questionId, optionId) => {
        setAnswers(prev => {
            const current = prev[questionId] || [];
            const updated = current.includes(optionId)
                ? current.filter(id => id !== optionId)
                : [...current, optionId];

            const newAnswers = { ...prev, [questionId]: updated };

            // Clear error when answered
            if (updated.length > 0) {
                setErrors(prev => ({ ...prev, [questionId]: false }));
            }

            return newAnswers;
        });
    };

    const handleSubmit = async () => {
        // Validate
        const newErrors = {};
        test.intrebari.forEach(q => {
            if (!answers[q.id] || answers[q.id].length === 0) {
                newErrors[q.id] = true;
            }
        });

        setErrors(newErrors);

        if (Object.keys(newErrors).length > 0) {
            alert("Please answer all required questions!");
            return;
        }

        setIsSubmitting(true);

        try {
            // Send answers and get result
            const usernameToSend = user ? user.username : guestUsername
            const result = await testeService.submitTest(id, answers, usernameToSend);

            // Navigate to result page with data
            navigate(`/teste/${id}/result`, {
                state: { result, testTitle: test.titlu }
            });

        } catch (error) {
            console.error("Submission failed", error);
            alert("Failed to submit test. Please try again.");
        } finally {
            setIsSubmitting(false);
        }
    };

    if (!test) return <p>Loading test...</p>;

    return (
        <div className="test-with-users-layout">
            {/* LEFT side: test content */}
            <div className="test-content">
                <button className="teste-btn" onClick={() => navigate("/teste")}>Back</button>
                <h2>{test.titlu}</h2>
                <p className="teste-small">
                    Created: <strong>{test.dataCrearii}</strong>
                </p>

                {guestUsername && (
                    <p style={{ color: "#555", fontStyle: "italic" }}>
                        You are taking this test as: <strong>{guestUsername}</strong>
                    </p>
                )}

                <hr />

                {test.intrebari.map((q) => (
                    <div key={q.id} className="take-test-question">
                        <h3>
                            {"(" + q.punctaj + "p)" + " " + q.continut}
                            <span style={{ color: "red", marginLeft: "8px" }}>*</span>
                        </h3>

                        <div className="options-container">
                            {q.optiuni.map((opt) => (
                                <label key={opt.id} className="take-test-option">
                                    <input
                                        type="checkbox"
                                        checked={answers[q.id]?.includes(opt.id) || false}
                                        onChange={() => handleCheckboxChange(q.id, opt.id)}
                                        disabled={isSubmitting}
                                    />
                                    <span className="option-text">{opt.continut}</span>
                                </label>
                            ))}
                        </div>

                        {errors[q.id] && (
                            <p style={{ color: "red", fontSize: "14px", margin: "8px 0 0" }}>
                                Warning: Answer required
                            </p>
                        )}
                    </div>
                ))}

                <button
                    className="teste-btn"
                    style={{ marginTop: "30px" }}
                    onClick={handleSubmit}
                    disabled={isSubmitting}
                >
                    {isSubmitting ? "Submitting..." : "Submit Test"}
                </button>
            </div>

            <ActiveUsers testId={id} guestUsername={guestUsername}/>

        </div>
    );
};

export default TakeTest;