import java.io.*;
import java.net.*;

public class Client {
    private String reponse;
    private int num;
    private String message;

    public Client(){
        reponse ="";
        num=0;
        message="";
    }
    public void setNum(int num){
        this.num=num;
    }
    //maadesh l'interface graphique taayet l information(), l'interface tjiha ken lreponse, w lezem nthabtou fel exceptions
    public String getReponse() throws IOException{
            information();
        return reponse;
    }

    public String getMessage(){
        return message;
    }
    private void information() throws IOException{
        // This is the function eli ki nenzlou aal button tekhdem
        // Tache Mtaa l function is to send data lel server hasb ma fhemt
        //7aja marbouta bl base de donne bla bla mnaarfsh naamlha
            Socket s = new Socket("localhost", 5000);
            ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
            // so basically il star he4a lzmna naamlou new student fl bd dont know how
            // so it needs change
            p.writeInt(num); // Sending just the id not a Class
            p.flush();
            // Here we read the details from server
            BufferedReader response = new BufferedReader(new InputStreamReader(
                    s.getInputStream()));
            reponse = response.readLine();
            p.close();
            response.close();
            s.close();
    }

}