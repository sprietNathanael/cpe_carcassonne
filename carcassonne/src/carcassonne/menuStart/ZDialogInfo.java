package carcassonne.menuStart;

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
        this.nom2 = nom;
        this.colors2 = colors;
        this.nom3 = nom;
        this.colors3 = colors;
        this.nom4 = nom;
        this.colors4 = colors;
        this.nom5 = nom;
        this.colors5 = colors;
        this.nom6 = nom;
        this.colors6 = colors;
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
            str = "Description de l'objet InfoZDialog";
            str += "Nombres de joueurs : " + this.nombreJoueurs + "\n";
            str += "Nom Player 1: " + this.nom + "\n";
            str += "Couleur Player 1: " + this.colors + "\n";
            str += "Nom Player 2 : " + this.nom2 + "\n";
            str += "Couleur Player 2 : " + this.colors2 + "\n";
            str += "Nom Player 3: " + this.nom3 + "\n";
            str += "Couleur Player 3: " + this.colors3 + "\n";
            str += "Nom Player 4 : " + this.nom4 + "\n";
            str += "Couleur Player 4 : " + this.colors4 + "\n";
            str += "Nom Player 5: " + this.nom5 + "\n";
            str += "Couleur Player 5 : " + this.colors5 + "\n";
            str += "Nom Player 6 : " + this.nom6 + "\n";
            str += "Couleur Player 6 : " + this.colors6 + "\n";
        }
        else {
            str = "Aucune information !";
        }
        return str;
    }
}
