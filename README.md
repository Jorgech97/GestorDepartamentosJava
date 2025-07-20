# 🏬 Sistema de Gestión de Tienda por Departamentos

Proyecto académico desarrollado en Java con NetBeans que implementa estructuras de datos lineales (pilas y colas) utilizando **arreglos unidimensionales**, con una **interfaz gráfica de usuario (GUI)** para la gestión de departamentos y artículos en una tienda.

## 🎯 Objetivo del Proyecto

- Comprender e implementar pilas y colas con arreglos estáticos.
- Aplicar principios LIFO (Last In First Out) y FIFO (First In First Out).
- Gestionar objetos interrelacionados (departamentos y artículos) en una estructura jerárquica.
- Utilizar componentes GUI para la interacción dinámica del usuario.

## 📚 Contenidos Técnicos

- Arreglos unidimensionales (hasta 20 elementos).
- Implementación de **pilas** para registrar departamentos.
- Implementación de **colas** para manejar artículos dentro de cada departamento.
- Java Swing para la creación de interfaces gráficas.
- Validaciones y control de errores con excepciones.

## 🛠️ Requisitos Técnicos

- **Java JDK:** Última versión disponible desde [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).
- **NetBeans IDE:** Último release desde [Apache NetBeans](https://netbeans.apache.org/).
- No se permite el uso de `ArrayList`, `Stack`, `Queue` ni ninguna colección de Java.
- El uso de `JOptionPane` está restringido exclusivamente a mensajes de error o excepciones.

---

## 🖼️ Funcionalidades y Estructura del Sistema

### 1. Registro de Departamentos (PILA)

- Permite registrar hasta 20 departamentos.
- Implementado como una **pila** (último en entrar, primero en salir).
- Cada departamento tiene:
  - `ID`: Entero generado automáticamente (autoincremental).
  - `Nombre`: Cadena de texto.
  - Cola de artículos vacía al inicio.

📌 *Visualización en tabla (`JTable`)* de todos los departamentos registrados.

---

### 2. Registro de Artículos (COLA por Departamento)

- El usuario selecciona un departamento desde la tabla.
- Se permite registrar artículos vinculados al departamento.
- Campos de artículo:
  - `ID`: Entero generado automáticamente (único entre todos).
  - `Nombre`: Texto.
  - `Categoría`: Seleccionable de lista (`JComboBox`) con valores como Ropa, Electrónica, Hogar, etc.

🔁 Cada departamento mantiene una **cola** de artículos con capacidad de 20.

---

### 3. Eliminación de Artículos

- Elimina automáticamente el **primer artículo** registrado del departamento seleccionado (no seleccionable por usuario).
- Se actualiza la tabla de artículos después de cada eliminación.

---

### 4. Traslado de Artículos entre Departamentos

- Transfiere la **cola completa** de artículos de un departamento origen a otro destino.
- Validaciones incluidas:
  - Origen ≠ Destino.
  - Ambos departamentos deben existir.
  - El origen debe tener artículos.
- Los artículos son transferidos manteniendo el orden FIFO.

---

### 5. Eliminación de Departamentos

- Elimina el **último departamento registrado** si **su cola de artículos está vacía**.
- Se actualiza la visualización de departamentos después de cada eliminación.

---

## 📂 EJEMPLOS
<img width="888" height="591" alt="image" src="https://github.com/user-attachments/assets/8a36eebf-0ff0-4834-8a63-7233fdb81abc" />

<img width="885" height="592" alt="image" src="https://github.com/user-attachments/assets/028d9e1d-2961-4d18-be2f-f7549577f352" />

<img width="896" height="188" alt="image" src="https://github.com/user-attachments/assets/93f81f8f-bc30-41d5-ba14-559c34e16502" />

<img width="887" height="134" alt="image" src="https://github.com/user-attachments/assets/c11d7849-aa0e-4589-8dca-095f64191f97" />

<img width="889" height="139" alt="image" src="https://github.com/user-attachments/assets/982f4064-7ee2-4b42-aef8-bc4f6e435290" />





