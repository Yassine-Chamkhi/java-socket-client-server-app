import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InterfaceGraphique extends JFrame implements ActionListener {

    public static void main(String[] args) {
        InterfaceGraphique ihm = new InterfaceGraphique();
    }


    private Client client;
    private JButton boutonOK;
    private JTextField inputId;
    private JLabel intro;
    private JLabel outro;
    private JLabel message;
    private JLabel idLabel;
    private JLabel prenom;
    private JLabel nom;
    private JLabel email;
    private JLabel filiere;
    private JLabel groupe;
    private JLabel moyenne;

    public InterfaceGraphique(){
        client = new Client();
        this.setTitle("Student Data");
        this.setSize(270, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        intro = new JLabel("ID Etudiant");
        intro.setBounds(10, 20, 90, 21);
        add(intro);

        inputId = new JTextField();
        inputId.setBounds(10, 40, 90, 21);
        add(inputId);

        message = new JLabel("Bienvenue! Entrez un ID");
        Font f=message.getFont();
        message.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        message.setBounds(15, 60,260 , 21);
        add(message);


        boutonOK = new JButton("OK");
        boutonOK.setBounds(110, 40, 60, 21);
        boutonOK.addActionListener(this);
        add(boutonOK);

        outro = new JLabel("Informations Etudiant:");
        outro.setBounds(10, 95, 290, 21);
        add(outro);

        initInfo();

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(boutonOK)) {
            reinitInfo();
            try{
                String[] arrOfstr;
                int id = Integer.parseInt(inputId.getText());
                client.setNum(id);
                try {
                    String result=client.getReponse();
                    arrOfstr= result.split(":");
                    if(arrOfstr[0].equals("N/A")){
                        message.setForeground(Color.red);
                        message.setText("ID n existe pas");
                    }
                    else {
                        message.setForeground(Color.black);
                        message.setText("Succes");
                        updateInfo(id,arrOfstr);
                    }
                }catch(IOException e1){
                    message.setForeground(Color.red);
                    message.setText("Serveur non disponible");
                }
            }
            catch(NumberFormatException e1){
                message.setForeground(Color.red);
                message.setText("Entrez un id numerique!");
            }

        }
    }

    private void initInfo(){
        idLabel = new JLabel("<html><u>ID:</u>  </html>");
        Font f=idLabel.getFont();
        idLabel.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        idLabel.setBounds(15, 115, 300, 21);

        prenom = new JLabel("<html><u>Prenom:</u>  </html>");
        f=prenom.getFont();
        prenom.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        prenom.setBounds(15, 135, 300, 21);

        nom = new JLabel("<html><u>Nom:</u>  </html>");
        f=nom.getFont();
        nom.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        nom.setBounds(15, 155, 300, 21);

        email = new JLabel("<html><u>Email:</u>  </html>");
        f=email.getFont();
        email.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        email.setBounds(15, 175, 300, 21);

        filiere = new JLabel("<html><u>Filiere:</u>  </html>");
        f=filiere.getFont();
        filiere.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        filiere.setBounds(15, 195, 300, 21);

        groupe = new JLabel("<html><u>Groupe:</u>  </html>");
        f=groupe.getFont();
        groupe.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        groupe.setBounds(15, 215, 300, 21);

        moyenne = new JLabel("<html><u>Moyenne:</u>  </html>");
        f=moyenne.getFont();
        moyenne.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
        moyenne.setBounds(15, 235, 300, 21);

        add(idLabel);
        add(prenom);
        add(nom);
        add(email);
        add(filiere);
        add(groupe);
        add(moyenne);
    }

    private void reinitInfo(){
        idLabel.setText("<html><u>ID:</u>  </html>test");
        prenom.setText("<html><u>Prenom:</u>  </html>");
        nom.setText("<html><u>Nom:</u>  </html>");
        email.setText("<html><u>Email:</u>  </html>");
        filiere.setText("<html><u>Filiere:</u>  </html>");
        groupe.setText("<html><u>Groupe:</u>  </html>");
        moyenne.setText("<html><u>Moyenne:</u>  </html>");
    }

    private void updateInfo(int id, String[] arr){
        idLabel.setText("<html><u>ID:</u>  "+id+"</html>");
        prenom.setText("<html><u>Prenom:</u>  "+arr[0]+"</html>");
        nom.setText("<html><u>Nom:</u>  "+arr[1]+"</html>");
        email.setText("<html><u>Email:</u>  "+arr[2]+"</html>");
        filiere.setText("<html><u>Filiere:</u>  "+arr[3]+"</html>");
        groupe.setText("<html><u>Groupe:</u>  "+arr[4]+"</html>");
        moyenne.setText("<html><u>Moyenne:</u>  "+arr[5]+"</html>");
    }
}