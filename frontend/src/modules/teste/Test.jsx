import React from "react";
import { useParams } from "react-router-dom";
import "./styles/Test.css";

const Test = () => {
    const { id } = useParams();

    return (
        <div className="teste-single">
            <h2>Test #{id}</h2>
            <p>This is an example test. You can start it below.</p>

            <button className="teste-take-btn">
                Take Test
            </button>
        </div>
    );
};

export default Test;
