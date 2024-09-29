package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    private Socket socket;
    private final String host;

    public EmailSender(String host, int port) throws IOException, InterruptedException {
        // Otevření socketu
        this.host = host;
        socket = new Socket(host, port);
        System.out.println("Pripojeno na " + host + ":" + port);
    }
    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException, InterruptedException {

        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        String[] commands = new String[]{"EHLO " + host + "\r\n",  "MAIL FROM:<" + from + ">\r\n", "RCPT TO:<" + to + ">\r\n", "DATA\r\n", "Subject: " + subject + "\r\n" + "\r\n", text + "\r\n",  ".\r\n"};

        for (String command : commands) {
            out.write(command.getBytes());
            System.out.println("Sending: " + command);
            Thread.sleep(500);
            byte[] response = new byte[1024];

            if (in.available() > 0) {
                int len = in.read(response);
                System.out.write(response, 0, len);
            }
        }
    }
    /*
     * Sends QUIT and closes the socket
     */
    public void close() {

        try {
           OutputStream out = socket.getOutputStream();
           out.write("QUIT\r\n".getBytes());
           socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}