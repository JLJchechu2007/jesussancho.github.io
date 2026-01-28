# 🍦 Heladería — Business Management Application (Java)

📍 Madrid, Spain · Author / Autor: **Jesús Sancho Hagen**

Business management application developed in **Java**, designed using a **3-phase architecture**:
**Presentation – Business – Integration**, with information exchanged through **Transfers (DTOs)**.

Aplicación de gestión de negocio desarrollada en **Java**, diseñada siguiendo un **modelo de 3 fases**:
**Presentación – Negocio – Integración**, comunicadas mediante **Transfers (DTOs)**.

---

## 🧩 Architecture model / Modelo de arquitectura

**EN:**  
The application follows a **strict separation of responsibilities** using a three-phase model. Each layer has a well-defined role and communicates with the others only through Transfers, avoiding tight coupling.

**ES:**  
La aplicación sigue una **separación estricta de responsabilidades** mediante un modelo de tres fases. Cada capa tiene un rol bien definido y se comunica con las demás únicamente mediante Transfers, evitando acoplamientos fuertes.


---

## 🖥️ Presentation layer / Capa de presentación

**EN:**  
Responsible for user interaction. It gathers input, displays results, and delegates all operations to the business layer via the controller interface.  
No business logic is implemented here.

**ES:**  
Responsable de la interacción con el usuario. Recoge la entrada, muestra resultados y delega todas las operaciones a la capa de negocio a través del controlador.  
No contiene lógica de negocio.

- Entry point / Punto de entrada: `main_final.java`
- Depends only on the **Controller interface**
- Communicates using **Transfers**

---

## 🧠 Business layer / Capa de negocio

**EN:**  
Contains the **core business logic** of the application. It validates operations, coordinates workflows, and decides when integration services are required.

**ES:**  
Contiene la **lógica de negocio principal**. Valida operaciones, coordina flujos y decide cuándo es necesario acceder a la capa de integración.

- `Controlador` (interface)
- `ControladorImp` (implementation)
- Completely independent from UI and persistence details
- Uses Transfers as input/output objects

---

## 🗄️ Integration layer / Capa de integración

**EN:**  
Handles data persistence and access. In this project, **JSON files are used as a low-level database**, isolating storage details from the rest of the system.

**ES:**  
Gestiona el acceso y la persistencia de datos. En este proyecto se utilizan **archivos JSON como base de datos de bajo nivel**, aislando los detalles de almacenamiento del resto del sistema.

- Read/write operations over JSON
- Triggered only by the business layer
- No direct dependency with presentation

---

## 🔁 Transfers (DTOs)

**EN:**  
Transfers (Data Transfer Objects) are simple data containers used to move information between layers. They define clear data contracts and prevent layers from depending on internal implementations.

**ES:**  
Los Transfers (Data Transfer Objects) son contenedores de datos simples usados para transportar información entre capas. Definen contratos claros y evitan dependencias entre implementaciones internas.

Characteristics / Características:
- No business logic
- Only attributes and accessors
- Shared between presentation and business layers

---

## ▶️ Execution flow / Flujo de ejecución

**EN:**
1. User interacts with the presentation layer
2. Presentation creates Transfers and calls the Controller
3. Business layer processes the request
4. Integration layer accesses JSON if needed
5. Results are returned via Transfers
6. Presentation displays the output

**ES:**
1. El usuario interactúa con la capa de presentación
2. Presentación crea Transfers y llama al Controlador
3. La capa de negocio procesa la petición
4. La capa de integración accede a JSON si es necesario
5. Los resultados vuelven mediante Transfers
6. La presentación muestra el resultado

---

## 🛠️ Key concepts / Conceptos clave

- Java OOP
- 3-phase / 3-layer architecture
- Controller pattern
- Transfer / DTO pattern
- JSON persistence
- Separation of concerns

---

## 📝 Notes / Notas

**EN:**  
This project emphasizes architectural clarity and clean design over framework usage. The layered approach makes the application easy to maintain, test, and extend.

**ES:**  
Este proyecto prioriza la claridad arquitectónica y el diseño limpio frente al uso de frameworks. El enfoque en capas facilita el mantenimiento, las pruebas y la extensibilidad.

**EN:**  
The integration layer can be replaced by a real database without modifying presentation or business logic.

**ES:**  
La capa de integración puede sustituirse por una base de datos real sin modificar la lógica de presentación ni de negocio.

---

## 📚 Technologies / Tecnologías

- Java
- JSON
- Object-Oriented Programming
- Layered Architecture
- DTO / Transfer pattern

---

## 📄 License / Licencia

Add your preferred license (MIT / GPL / etc.) or remove this section.  
Añade la licencia que prefieras (MIT / GPL / etc.) o elimina esta sección.
