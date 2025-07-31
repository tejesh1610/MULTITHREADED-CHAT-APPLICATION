# Multithreaded Chat Application 

A Java-based multithreaded chat system that allows multiple clients to connect to a central server and exchange messages in real time. Built using socket programming and threading concepts.

##  Features

- Real-time messaging between multiple clients
- Server-side broadcasting of messages
- Graceful client exit handling
- Thread-safe client management
- Console-based interface

## 🛠 Technologies Used

- **Java**
- **Socket Programming**
- **Multithreading**
- **Data Streams (DataInputStream, DataOutputStream)**

##  Project Structure
├── ChatServer.java       # Server-side logic ├── ChatClient.java       # Client-side logic ├── README.md             # Project documentation


## 📡 How It Works

### Server (`ChatServer.java`)
- Listens on port `1234` for incoming connections.
- For each client, spawns a new thread (`ClientHandler`) to manage communication.
- Maintains a list of active clients and broadcasts messages to all.

### Client (`ChatClient.java`)
- Connects to the server on `localhost:1234`.
- Starts two threads:
  - **Send Thread**: Reads user input and sends it to the server.
  - **Receive Thread**: Displays messages received from the server.

##  How to Run

1. **Compile the files**:
   ```bash
   javac ChatServer.java ChatClient.java
2. Start the server:
  Bash
  java ChatServer

3.Start clients in separate terminals:
java ChatClient

4. Type messages and chat away!
- Type exit to disconnect a client.

 Notes
- All clients must connect to the same server IP and port.
- Ensure firewall settings allow communication on the chosen port.
   License
This project is open-source and available under the MIT License.
