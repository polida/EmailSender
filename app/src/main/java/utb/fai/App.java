package utb.fai;

public class App {

    public static void main(String[] args) {
        // TODO: Implement input parameter processing
        
        try {
            EmailSender sender = new EmailSender("smtp.utb.cz", 25);
            sender.send("d_polisensky@utb.cz", "d_polisensky@utb.cz", "Email from Java", "Funguje to?\nSnad...");
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
