### 🔧 How to Run the Backend Project

Follow these steps to set up and run the **backend** project locally:

---

#### 1. Clone the Repository

```bash
git clone https://github.com/alejoeperez/item-detail-backend.git
cd item-detail-backend
```

---

#### 2. Switch to the `develop` Branch

```bash
git checkout develop
```

---

#### 3. Requirements

Ensure you have the following installed:

- Java 17
- Maven 3.9.9
- IDE (e.g., IntelliJ IDEA or Spring Tool Suite)

---

#### 4. Import the Project

Open your IDE and import the project as a **Maven Project**.

---

#### 5. Install Dependencies

Run the following command to download all dependencies:

```bash
mvn clean install -U
```

---

#### 6. Run the Application

Locate the `ItemDetailBackendApplication.java` class in:

```
item-detail-backend/src/main/java/com/meli/item_detail_backend/
```

Right-click the file and select `Run` or run it via your IDE's console.

The backend will be deployed at: 📍 `http://localhost:8080`

---

#### 7. API Documentation

Once running, access the API documentation at: 📍 `http://localhost:8080/swagger-ui/index.html`

