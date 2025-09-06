/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import megamek.logging.MMLogger;

public class SingleInstanceService {
    private static final MMLogger LOGGER = MMLogger.create(SingleInstanceService.class);
    private final String applicationId;
    private final int port;
    private FileLock fileLock;
    private FileChannel fileChannel;
    private RandomAccessFile raf;
    private ServerSocket serverSocket;
    private MessageHandler messageHandler;
    private Thread listenerThread;
    private boolean isRunning = false;
    private final ExecutorService connectionExecutor = Executors.newFixedThreadPool(1);

    /**
     * Functional interface for message handling
     */
    @FunctionalInterface
    public interface MessageHandler {
        void handleMessage(String message);
    }

    /**
     * Creates a new single instance service
     *
     * @param applicationId Unique identifier for this application
     */
    public SingleInstanceService(String applicationId) {
        this(applicationId, 0);
    }

    /**
     * Creates a new single instance service with specific port
     *
     * @param applicationId Unique identifier for this application
     * @param port          Port to use for communication
     */
    public SingleInstanceService(String applicationId, int port) {
        this.applicationId = applicationId;
        this.port = port;
    }

    /**
     * Register this as the primary instance
     *
     * @return true if this is the first instance, false otherwise
     */
    public boolean register() {
        Path lockFile = getLockFilePath();
        Path portFile = getPortFilePath();

        try {
            // Ensure parent directory exists
            Files.createDirectories(lockFile.getParent());
            raf = new RandomAccessFile(lockFile.toFile(), "rw");
            fileChannel = raf.getChannel();
            fileLock = fileChannel.tryLock();

            if (fileLock == null) {
                // Another instance has the lock
                return false;
            }

            // We got the lock, now start the server
            serverSocket = new ServerSocket(port, 10, InetAddress.getLoopbackAddress());
            int actualPort = serverSocket.getLocalPort();

            // Write the port number to a separate file (not the locked file)
            Files.writeString(portFile, String.valueOf(actualPort));

            // Start listening for connections
            startListener();
            isRunning = true;
            return true;

        } catch (Exception e) {
            LOGGER.error("Error registering single instance", e);
            cleanup();
            // In case of error, assume we're the only instance
            return true;
        }
    }

    /**
     * Send a message to the running instance
     *
     * @param message Message to send
     */
    public void sendMessage(String message) {
        Path portFile = getPortFilePath();

        try {
            // Check if the port file exists
            if (!Files.exists(portFile)) {
                return;
            }

            // Read the port number from the port file (not the lock file)
            String portStr = Files.readString(portFile).trim();
            int port = Integer.parseInt(portStr);

            // Connect to the server
            try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), port);
                  PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                // Send the message
                out.println(message);
            }

        } catch (Exception e) {
            LOGGER.error("Error sending message to running instance", e);
        }
    }

    /**
     * Sets a handler for messages from other instances
     *
     * @param handler Handler to process messages
     */
    public void setMessageHandler(MessageHandler handler) {
        this.messageHandler = handler;
    }

    /**
     * Start the listener thread
     */
    private void startListener() {
        listenerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted() && serverSocket != null) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    connectionExecutor.submit(() -> {
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                            String message = in.readLine();
                            if (message != null && messageHandler != null) {
                                messageHandler.handleMessage(message);
                            }

                        } catch (Exception e) {
                            LOGGER.error("Error processing client connection", e);
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (Exception e) {
                                // Ignore
                            }
                        }
                    });

                } catch (Exception e) {
                    if (!Thread.currentThread().isInterrupted()) {
                        LOGGER.error("Error accepting client connection", e);
                    }
                }
            }
        });
        // Set the thread as a daemon and start it
        listenerThread.setDaemon(true);
        listenerThread.setName("SingleInstance-Listener");
        listenerThread.start();
    }

    /**
     * Get the path to the lock file
     */
    private Path getLockFilePath() {
        return getAppDirectory().resolve(applicationId + ".lock");
    }

    /**
     * Get the path to the port file
     */
    private Path getPortFilePath() {
        return getAppDirectory().resolve(applicationId + ".port");
    }

    /**
     * Get the application directory
     */
    private Path getAppDirectory() {
        String userHome = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();

        Path dir;
        if (os.contains("win")) {
            // Windows: %APPDATA%\.megameklab
            dir = Paths.get(System.getenv("APPDATA"), ".megameklab");
        } else if (os.contains("mac")) {
            // macOS: ~/Library/Application Support/MegaMekLab
            dir = Paths.get(userHome, "Library", "Application Support", "MegaMekLab");
        } else {
            // Linux/Unix: ~/.megameklab
            dir = Paths.get(userHome, ".megameklab");
        }

        return dir;
    }

    /**
     * Clean up resources
     */
    public void cleanup() {
        isRunning = false;

        if (listenerThread != null) {
            listenerThread.interrupt();
        }

        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            LOGGER.error("Error closing server socket", e);
        }

        try {
            if (fileLock != null) {
                fileLock.release();
            }
        } catch (Exception e) {
            LOGGER.error("Error releasing file lock", e);
        }
        try {
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (raf != null) {
                raf.close();
            }
        } catch (Exception e) {
            LOGGER.error("Error closing file channel", e);
        }
        // Clean up the port file
        try {
            Files.deleteIfExists(getPortFilePath());
        } catch (Exception e) {
            LOGGER.error("Error deleting port file", e);
        }
        // Shut down the executor service
        if (connectionExecutor != null) {
            connectionExecutor.shutdown();
            try {
                if (!connectionExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                    connectionExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                connectionExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Check if the service is running
     */
    public boolean isRunning() {
        return isRunning;
    }
}
