import React, { useState, useEffect } from "react";
import { useAuth } from "../../context/AuthContext";
import domeniiService from "../../services/domeniiService";
import testeService from "../../services/testeService";
import "./styles/CreateTest.css";

const CreateTest = ({ onClose, testId = null }) => {
  const { user } = useAuth();
  const [domenii, setDomenii] = useState([]);

  // Left side - Test info
  const [testTitle, setTestTitle] = useState("");
  const [selectedDomeniu, setSelectedDomeniu] = useState("");

  // Middle side - Question form
  const [questionContent, setQuestionContent] = useState("");
  const [options, setOptions] = useState([
    { id: null, continut: "", punctaj: 0, isCorrect: false },
    { id: null, continut: "", punctaj: 0, isCorrect: false },
    { id: null, continut: "", punctaj: 0, isCorrect: false },
    { id: null, continut: "", punctaj: 0, isCorrect: false }
  ]);

  // Right side - Questions list
  const [questions, setQuestions] = useState([]);
  const [selectedQuestionId, setSelectedQuestionId] = useState(null);

  // Editing state
  const [isEditingQuestion, setIsEditingQuestion] = useState(false);
  const [editingQuestionId, setEditingQuestionId] = useState(null);
  const [isLoading, setIsLoading] = useState(testId ? true : false);
  const [isEditingTest, setIsEditingTest] = useState(!!testId);

  useEffect(() => {
    loadDomenii();
    if (testId) {
      loadTestData();
    }
  }, []);

  const loadDomenii = async () => {
    try {
      const res = await domeniiService.getAll();
      setDomenii(res);
      if (res.length > 0 && !testId) {
        setSelectedDomeniu(res[0].id);
      }
    } catch (err) {
      console.error("Încărcarea domeniilor a eșuat:", err);
    }
  };

  const loadTestData = async () => {
    try {
      const testData = await testeService.getFull(testId);
      setTestTitle(testData.titlu);
      setSelectedDomeniu(testData.idDomeniu);

      // Map intrebari to questions format
      const mappedQuestions = testData.intrebari.map(intrebare => ({
        id: intrebare.id,
        continut: intrebare.continut,
        optiuni: intrebare.optiuni
      }));

      setQuestions(mappedQuestions);
      setIsLoading(false);
    } catch (err) {
      console.error("Încărcarea datelor testului a eșuat:", err);
      setIsLoading(false);
    }
  };

  const handleOptionChange = (index, field, value) => {
    const newOptions = [...options];
    if (field === "punctaj") {
      newOptions[index][field] = value === "" ? 0 : Number(value);
    } else if (field === "isCorrect") {
      newOptions[index][field] = value;
    } else {
      newOptions[index][field] = value;
    }
    setOptions(newOptions);
  };

  const resetQuestionForm = () => {
    setQuestionContent("");
    setOptions([
      { id: null, continut: "", punctaj: 0, isCorrect: false },
      { id: null, continut: "", punctaj: 0, isCorrect: false },
      { id: null, continut: "", punctaj: 0, isCorrect: false },
      { id: null, continut: "", punctaj: 0, isCorrect: false }
    ]);
    setIsEditingQuestion(false);
    setEditingQuestionId(null);
    setSelectedQuestionId(null);
  };

  const handleCreateQuestion = () => {
    if (!questionContent.trim()) {
      alert("Te rog să introduci conținutul întrebării");
      return;
    }

    const hasContent = options.some(opt => opt.continut.trim());
    if (!hasContent) {
      alert("Te rog să introduci cel puțin o opțiune");
      return;
    }

    const filteredOptions = options.filter(opt => opt.continut.trim());

    if (isEditingQuestion && editingQuestionId !== null) {
      // Update existing question
      setQuestions(
          questions.map(q =>
              q.id === editingQuestionId
                  ? {
                    ...q,
                    continut: questionContent,
                    optiuni: filteredOptions
                  }
                  : q
          )
      );
      setSelectedQuestionId(editingQuestionId);
    } else {
      // Create new question
      const newQuestion = {
        id: Date.now(),
        continut: questionContent,
        optiuni: filteredOptions
      };
      setQuestions([...questions, newQuestion]);
      setSelectedQuestionId(newQuestion.id);
    }

    resetQuestionForm();
  };

  const handleSelectQuestion = (questionId) => {
    setSelectedQuestionId(questionId);
    const question = questions.find(q => q.id === questionId);
    if (question) {
      setQuestionContent(question.continut);
      const newOptions = [
        { id: null, continut: "", punctaj: 0, isCorrect: false },
        { id: null, continut: "", punctaj: 0, isCorrect: false },
        { id: null, continut: "", punctaj: 0, isCorrect: false },
        { id: null, continut: "", punctaj: 0, isCorrect: false }
      ];
      question.optiuni.forEach((opt, idx) => {
        if (idx < 4) {
          newOptions[idx] = { ...opt, id: opt.id || null };
        }
      });
      setOptions(newOptions);
      setIsEditingQuestion(true);
      setEditingQuestionId(questionId);
    }
  };

  const handleDeleteQuestion = (questionId) => {
    setQuestions(questions.filter(q => q.id !== questionId));
    if (selectedQuestionId === questionId) {
      resetQuestionForm();
    }
  };

  const handleSubmitTest = async () => {
    if (!testTitle.trim()) {
      alert("Te rog să introduci titlul testului");
      return;
    }

    if (questions.length === 0) {
      alert("Te rog să adaugi cel puțin o întrebare");
      return;
    }

    const testData = {
      titlu: testTitle,
      idUser: user.id,
      idDomeniu: selectedDomeniu,
      intrebari: questions.map(q => ({
        continut: q.continut,
        optiuni: q.optiuni
      }))
    };

    try {
      if (isEditingTest) {
        await testeService.update(testId, testData);
        alert("Testul a fost actualizat cu succes!");
      } else {
        await testeService.create(testData);
        alert("Testul a fost creat cu succes!");
      }
      onClose();
    } catch (err) {
      console.error("Salvarea testului a eșuat:", err);
      alert("Salvarea testului a eșuat");
    }
  };

  if (isLoading) {
    return (
        <div className="create-test-overlay">
          <div className="loading-container">
            <p>Se încarcă datele testului…</p>
          </div>
        </div>
    );
  }

  return (
      <div className="create-test-overlay">
        <div className="create-test-container">
          {/* LEFT SIDE - Test Info */}
          <div className="create-test-left">
            <div className="form-group">
              <label>Titlu Test</label>
              <input
                  type="text"
                  value={testTitle}
                  onChange={(e) => setTestTitle(e.target.value)}
                  placeholder="Introdu titlul testului"
                  className="form-input"
              />
            </div>

            <div className="form-group">
              <label>Domeniu</label>
              <select
                  value={selectedDomeniu}
                  onChange={(e) => setSelectedDomeniu(e.target.value)}
                  className="form-select"
              >
                {domenii.map(d => (
                    <option key={d.id} value={d.id}>
                      {d.nume}
                    </option>
                ))}
              </select>
            </div>

            <div className="questions-count">
              <p>Întrebări: <strong>{questions.length}</strong></p>
            </div>

            <div className="action-buttons">
              <button
                  className="btn btn-primary"
                  onClick={handleSubmitTest}
                  disabled={questions.length === 0 || !testTitle.trim()}
              >
                {isEditingTest ? "Actualizează Testul" : "Salvează Testul"}
              </button>
              <button className="btn btn-secondary" onClick={onClose}>
                Anulează
              </button>
            </div>
          </div>

          {/* MIDDLE SIDE - Question Form */}
          <div className="create-test-middle">
            <h2>{isEditingQuestion ? "Editează Întrebarea" : "Creează Întrebare"}</h2>

            <div className="form-group">
              <label>Conținut Întrebare</label>
              <textarea
                  value={questionContent}
                  onChange={(e) => setQuestionContent(e.target.value)}
                  placeholder="Introdu conținutul întrebării"
                  className="form-textarea"
                  rows="4"
              />
            </div>

            <div className="options-section">
              <label className="options-label">Opțiuni</label>
              {options.map((option, idx) => (
                  <div key={idx} className="option-row">
                    <input
                        type="text"
                        value={option.continut}
                        onChange={(e) =>
                            handleOptionChange(idx, "continut", e.target.value)
                        }
                        placeholder={`Opțiunea ${idx + 1}`}
                        className="option-content"
                    />

                    <input
                        type="number"
                        value={option.punctaj}
                        onChange={(e) =>
                            handleOptionChange(idx, "punctaj", e.target.value)
                        }
                        placeholder="Punctaj"
                        className="option-score"
                        disabled={!option.isCorrect}
                        min="0"
                    />

                    <label className="option-correct">
                      <input
                          type="checkbox"
                          checked={option.isCorrect}
                          onChange={(e) =>
                              handleOptionChange(idx, "isCorrect", e.target.checked)
                          }
                      />
                      Corect
                    </label>
                  </div>
              ))}
            </div>

            <button
                className="btn btn-success"
                onClick={handleCreateQuestion}
            >
              {isEditingQuestion ? "Salvează Întrebarea" : "Adaugă Întrebarea"}
            </button>

            {isEditingQuestion && (
                <button
                    className="btn btn-cancel"
                    onClick={resetQuestionForm}
                >
                  Anulează Editarea
                </button>
            )}
          </div>

          {/* RIGHT SIDE - Questions List */}
          <div className="create-test-right">
            <h2>Întrebări ({questions.length})</h2>
            <div className="questions-list">
              {questions.map((q, idx) => (
                  <div
                      key={q.id}
                      className={`question-item ${
                          selectedQuestionId === q.id ? "active" : ""
                      }`}
                      onClick={() => handleSelectQuestion(q.id)}
                  >
                    <div className="question-item-header">
                      <span className="question-number">{idx + 1}</span>
                      <p className="question-text">{q.continut.substring(0, 50)}...</p>
                    </div>

                    <button
                        className="btn-delete-small"
                        onClick={(e) => {
                          e.stopPropagation();
                          handleDeleteQuestion(q.id);
                        }}
                    >
                      ✕
                    </button>
                  </div>
              ))}
            </div>
          </div>
        </div>
      </div>
  );
};

export default CreateTest;