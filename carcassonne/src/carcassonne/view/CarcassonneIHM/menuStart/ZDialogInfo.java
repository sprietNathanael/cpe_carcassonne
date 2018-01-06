package carcassonne.view.CarcassonneIHM.menuStart;

public class ZDialogInfo
{

    private String nom, nombreJoueurs, colors, nom2, colors2, nom3, colors3, nom4, colors4, nom5, colors5, nom6, colors6;

    public ZDialogInfo()
    {
    }

    public ZDialogInfo(String nom, String nombreJoueurs, String colors,
            String nom2, String colors2,
            String nom3, String colors3,
            String nom4, String colors4,
            String nom5, String colors5,
            String nom6, String colors6)
    {
        this.nombreJoueurs = nombreJoueurs;
        this.nom = nom;
        this.colors = colors;
        this.nom2 = nom2;
        this.colors2 = colors2;
        this.nom3 = nom3;
        this.colors3 = colors3;
        this.nom4 = nom4;
        this.colors4 = colors4;
        this.nom5 = nom5;
        this.colors5 = colors5;
        this.nom6 = nom6;
        this.colors6 = colors6;
    }

    public String toString()
    {
        String str;
        if (this.nom != null && this.nombreJoueurs != null && this.colors != null
                && this.nom2 != null && this.colors2 != null
                && this.nom3 != null && this.colors3 != null
                && this.nom4 != null && this.colors4 != null
                && this.nom5 != null && this.colors5 != null
                && this.nom6 != null && this.colors6 != null) {
  
            str = "Nombres de joueurs : " + this.nombreJoueurs + "\n";
            str += "Player1 [ Nom : " + this.nom + " / Couleur : " + this.colors + " ] " + "\n";
            str += "Player2 [ Nom : " + this.nom2 + " / Couleur : " + this.colors2 + " ] " + "\n";
            str += "Player3 [ Nom : " + this.nom3 + " / Couleur : " + this.colors3 + " ] " + "\n";
            str += "Player4 [ Nom : " + this.nom4 + " / Couleur : " + this.colors4 + " ] " + "\n";
            str += "Player5 [ Nom : " + this.nom5 + " / Couleur : " + this.colors5 + " ] " + "\n";
            str += "Player6 [ Nom : " + this.nom6 + " / Couleur : " + this.colors6 + " ] " + "\n";
        }
        else {
            str = "Aucune information !";
        }
        return str;
    }
}
