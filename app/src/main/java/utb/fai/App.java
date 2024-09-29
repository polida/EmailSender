package utb.fai;

public class App {

    public static void main(String[] args) {
        // TODO: Implement input parameter processing
            String host, from, to, subject, text;
            int port;

        if (args.length  > 4) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            from = args[2];
            to = args[3];
            subject = args[4];
            text = args[5];
        } else {
            host = "smtp.utb.cz";
            port = 25;
            from = "d_polisensky@utb.cz";
            to = "d_polisensky@utb.cz";
            subject = "Email from Java";
            text = "Funguje to?\nSnad...";
        }

            try {
                EmailSender sender = new EmailSender(host, port);
                sender.send(from, to, subject, text);
                sender.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
