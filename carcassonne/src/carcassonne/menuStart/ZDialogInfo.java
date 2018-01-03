package carcassonne.menuStart;


public class ZDialogInfo {
  private String nom, sexe, age, cheveux, taille;

  public ZDialogInfo(){}
  public ZDialogInfo(String nom, String age, String cheveux){
    /*this.nom = nom;
    this.age = age;
    this.cheveux = cheveux;*/
  }

  public String toString(){
    String str;
    if(this.nom != null && this.age != null && this.cheveux != null){
      str = "Description de l'objet InfoZDialog";
      str += "Nom : " + this.nom + "\n";
      str += "Age : " + this.age + "\n"; //nombres players
      str += "Cheveux : " + this.cheveux + "\n"; // couleur
    }
    else{
      str = "Aucune information !";
    }
    return str;
  }
}