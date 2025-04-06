
# 📌 "Ahorca/World"

## 🎮 Introducción

Bienvenidos a la presentación del desarrollo de "Ahorca/World", un juego del ahorcado basada en mi propio diagrama entidad-relación. En esta presentación, se detalla cada uno de los componentes del sistema y su implementación en código Java con interfaz.

---


## 🚀 Implementación en Java con Interfaz

### 🔹 Tecnologías a Utilizar
- **Java** ☕
- **Swing** 🖥️ para la interfaz gráfica
- **AUN POR DECIDIR** 🗄️ para la conexión con base de datos
- **Visual Studio** 🏗️ para organizar el código

---

## 🗂️ 1.0 - Diagrama Entidad-Relación (E-R)

El diagrama E-R establece las relaciones entre los diferentes componentes del juego. A continuación, se explican las entidades principales:

![Tablero virtual con diagrama entidad-relación(1)](https://github.com/user-attachments/assets/3d4d3f59-0969-4d6f-9b5c-7a37f6cae4be)
![White Colorful Concept Map Chart](https://github.com/user-attachments/assets/d3a44134-e656-410f-9c0e-5fb673be0640)



---

| Entidad       | Atributos                                      | Relación                                                                 |
|--------------|----------------------------------------------|------------------------------------------------------------------------|
| **Jugador (JUGADOR)** | Nombre 📝,  Contraseña 🔑,  IDJugador 🆔,  IDAdmin 🛡️ (opcional) | Puede jugar varias partidas 🎲, obtener logros 🏆, aparecer en el ranking 📊 |
| **Administrador (ADMIN)** | IDAdmin 🆔, Rango ⭐, IDJugador 🆔 | Solo los jugadores con la contraseña de admin pueden acceder al rango |
| **Partida (PARTIDA)** | IDPartida 🆔, Resultado ✅❌, Puntuación 🎯, IDJugador 🆔, IDPalabra 🔤, IDJuego 🕹️ , IDnumJuego| Cada jugador puede participar en múltiples partidas; cada partida tiene una palabra asociada |
| **Logros (LOGROS)** | IDLogro 🆔, IDJugador 🆔 | Cada jugador puede desbloquear varios logros |
| **Ranking (RANKING)** | IDRanking 🆔, Rango 🏅, IDPartida 🆔 | Cada partida tiene una puntuación que influye en el ranking |
| **Palabra (PALABRA)** | IDPalabra 🆔, Sinónimo 1 📝, Sinónimo 2 📝, IDDificultad 🔥 | Cada palabra tiene una dificultad asignada |
| **Dificultad (DIFICULTAD)** | IDDificultad 🆔, Nivel 📶, Recompensa 🎁 | A mayor dificultad, mayor puntuación obtenida |
| **Modo de Juego (MODOJUEGO)** | IDJuego 🆔, Dificultad 🔥 | Cada partida pertenece a un modo de juego determinado |

---


## 📝 2.0 - BASE DE DATOS MySQL  


La base de datos de *Ahorca/World* está diseñada para gestionar jugadores, partidas, logros y rankings de manera eficiente. A continuación, se presenta la estructura optimizada en SQL:  


📌 Creación de la base de datos
```
CREATE DATABASE AhorcaWorld;
USE AhorcaWorld;
```
🎮 Tabla de Jugadores
```
CREATE TABLE Jugador (
    IDJugador INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Contraseña VARCHAR(255) NOT NULL,
    IDAdmin INT DEFAULT NULL
);
```
🛡️ Tabla de Administradores
```
CREATE TABLE Admin (
    IDAdmin INT PRIMARY KEY AUTO_INCREMENT,
    Rango VARCHAR(20) NOT NULL,
    IDJugador INT UNIQUE,
    FOREIGN KEY (IDJugador) REFERENCES Jugador(IDJugador)
);
```
🎲 Tabla de Partidas
```
CREATE TABLE Partida (
    IDPartida INT PRIMARY KEY AUTO_INCREMENT,
    Resultado ENUM('Ganado', 'Perdido') NOT NULL,
    Puntuación INT NOT NULL,
    IDJugador INT,
    IDPalabra INT,
    IDJuego INT,
    IDnumJuego INT,
    FOREIGN KEY (IDJugador) REFERENCES Jugador(IDJugador),
    FOREIGN KEY (IDPalabra) REFERENCES Palabra(IDPalabra),
    FOREIGN KEY (IDJuego) REFERENCES ModoJuego(IDJuego)
);
```
 🏆 Tabla de Logros
```
CREATE TABLE Logros (
    IDLogro INT PRIMARY KEY AUTO_INCREMENT,
    IDJugador INT,
    FOREIGN KEY (IDJugador) REFERENCES Jugador(IDJugador)
);
```
 📊 Tabla de Ranking
```
CREATE TABLE Ranking (
    IDRanking INT PRIMARY KEY AUTO_INCREMENT,
    Rango VARCHAR(20) NOT NULL,
    IDPartida INT,
    FOREIGN KEY (IDPartida) REFERENCES Partida(IDPartida)
);
```
 🔤 Tabla de Palabras
```
CREATE TABLE Palabra (
    IDPalabra INT PRIMARY KEY AUTO_INCREMENT,
    Palabra VARCHAR(50) NOT NULL,
    Sinonimo1 VARCHAR(50),
    Sinonimo2 VARCHAR(50),
    IDDificultad INT,
    FOREIGN KEY (IDDificultad) REFERENCES Dificultad(IDDificultad)
);
```
🔥 Tabla de Dificultad
```
CREATE TABLE Dificultad (
    IDDificultad INT PRIMARY KEY AUTO_INCREMENT,
    Nivel VARCHAR(10) NOT NULL,
    Recompensa INT NOT NULL
);
```
 🕹️ Tabla de Modo de Juego
```
CREATE TABLE ModoJuego (
    IDJuego INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    IDDificultad INT,
    FOREIGN KEY (IDDificultad) REFERENCES Dificultad(IDDificultad)
);
```
✅ **Base de Datos Lista**

---

#  3.0 Conectar la base de datos con Java. 

El juego tiene ACTUALMENTE las siguientes características:

- **Palabra aleatoria:** La palabra a adivinar se selecciona aleatoriamente 
- **Interfaz gráfica:** Utiliza `JFrame` y `JPanel` para mostrar diferentes componentes como etiquetas (`JLabel`), campos de texto (`JTextField`), y botones (`JButton`).
- **Imagen dinámica del ahorcado:** El muñeco ahorcado cambia con cada intento fallido, a sido creado y diseñado por mi mismo.
- **Ingreso de letras:** El jugador puede ingresar letras para intentar adivinar la palabra.
- **Letras usadas:** Se muestran las letras que el jugador ya ha intentado.
  

![image](https://github.com/user-attachments/assets/862f6a6c-45aa-4da0-b6e4-c362cf6f7d6e)

![image](https://github.com/user-attachments/assets/58807f93-5df1-4869-b6ac-e48d2645ef39)





🎯 **Próximo paso:** Crear el resto de niveles. 


¡Os mantendre informados! 💻🔥
