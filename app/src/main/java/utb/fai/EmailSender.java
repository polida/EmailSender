package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    private final Socket socket;


    public EmailSender(String host, int port) throws  IOException {
        this.socket = new Socket(host, port);

    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("HELO utb.cz");
        in.readLine();
        out.println("MAIL FROM: " + from);
        in.readLine();
        out.println("RCPT TO: " + to);
        in.readLine();
        out.println("DATA");
        in.readLine();
        out.println("Subject: " + subject);
        out.println(text);
        out.println(".");
        in.readLine();
    }

    /*
     * Sends QUIT and closes the socket
     */
    public void close() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("QUIT");
            in.readLine();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
