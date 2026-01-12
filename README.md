# Aplicatie Testare Online

Acest proiect este o aplicație web concepută pentru testare și chestionare online. Dispune de un backend securizat construit cu Spring Boot și un frontend modern și receptiv construit cu React și Vite.

## Structura Proiectului

- **Backend**: Aplicație Java Spring Boot situată în directorul rădăcină. Gestionează cererile API, autentificarea (JWT) și interacțiunile cu baza de date.
- **Frontend**: Aplicație React situată în directorul `frontend/`. Oferă interfața utilizator pentru susținerea testelor și gestionarea aplicației.

## Tehnologii Utilizate

- **Backend**:
  - Java
  - Spring Boot
  - Spring Security (JWT Authentication)
  - Spring Data JPA

- **Frontend**:
  - React
  - Vite

## Cerințe Preliminare

Înainte de a rula aplicația, asigurați-vă că aveți instalate următoarele:

- Java Development Kit (JDK) 17 sau mai nou
- Node.js (versiunea LTS recomandată)

## Ghid de Pornire

### 1. Configurare Backend

1. Deschideți un terminal în directorul rădăcină al proiectului.
2. Configurați conexiunea la baza de date în `src/main/resources/application.properties` dacă este necesar.
3. Rulați aplicația folosind Maven Wrapper:

   **Windows:**
   ```bash
   ./mvnw spring-boot:run
   ```

   **Linux/macOS:**
   ```bash
   ./mvnw spring-boot:run
   ```

   Serverul backend va porni pe `http://localhost:8080`.

### 2. Configurare Frontend

1. Deschideți o nouă fereastră de terminal.
2. Navigați în directorul frontend:
   ```bash
   cd frontend
   ```
3. Instalați dependențele necesare:
   ```bash
   npm install
   ```
4. Porniți serverul de dezvoltare:
   ```bash
   npm run dev
   ```

   Aplicația frontend va porni pe `http://localhost:5173` (sau portul specificat în terminal).