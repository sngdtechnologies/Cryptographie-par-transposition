/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DevellopeurWeb
 */
public class Crypter {
    private String text;
    private String cle;
    
    public Crypter(String t, String c){
        this.text = t;
        this.cle = c;
    }
    
    public String getTexteCrypter1(){
        //reception du texte a crypter et de la cles de cryptage
        String atexte = this.text;
        String cles = this.cle;
        /*
        |--------------------------------------------------------------------------
        | Algorithme de transposition
        |--------------------------------------------------------------------------
        |
        | /*--- P - o - c - e - d - e - r ---/*
        |
        | 1- Demande et Recuperation du texte à crypter ainsi que de la cles de cryptage
        | 2- Suppression des espaces que contient le texte
        | 3- Création et Remplissage d'un tableau associatif $assoc d'une suite de tableau de texte en clair ayant la meme longueur que la cles 
        | 4- Je range le tableau assosiatif par bloc et par ordre croissant de caractere de cles
        | 5- Transformation du tableau associatif final en haine de caratere
        | 6- Affichage de la chaine de caratere crypter
        |
        */
        //transformation du texte a crypter en majuscule
        atexte = atexte.toUpperCase();

        //transformation de la cles de cryptage en majuscule
        cles = cles.toUpperCase();

        //retirons les espaces dans le texte
        char[] texte = new char[atexte.length()];
        texte = atexte.toCharArray();
        String t = "";
        char space;
        int k = 0, a = 0, nbreSpace = 0;
        
        //determinons le nombre d'espace contenue dans le texte
        for(int i = 0; i < texte.length; i++) {
                space = texte[i];
                if(space == ' ') {
                        nbreSpace++;
                }
        }
        
        //initialisation du tableau qui contiendra les indices de chaque espace
        int[] indice = new int[nbreSpace];
        
        //cette boucle suprime les espaces dans le texte
        for(int i = 0; i < texte.length; i++) {
                space = texte[i];
                if(space == ' ') {
                        indice[a] = i;
                        a++;
                        for(int j = i; j < texte.length - 1; j++) {
                                texte[j] = texte[j + 1];
                        }
                }

                switch(texte[i]){
                    case 'à':
                            texte[i] = 'a';
                            break;
                    case 'â':
                            texte[i] = 'a';
                            break;
                    case 'é':
                            texte[i] = 'e';
                            break;
                    case 'è':
                            texte[i] = 'e';
                            break;
                    case 'ê':
                            texte[i] = 'e';
                            break;
                    case 'ç':
                            texte[i] = 'c';
                            break;
                    case 'î':
                            texte[i] = 'i';
                            break;
                    default: ;
                }
        }
        
        //cette boucle concatene les caractere du tableau dans une chaine de caractere "t"
        for(int b = 0; b < texte.length -a; b++) {
                t = t + texte[b];
        }

        //transformation de la chaine de caractere en tableau de caractere
        texte = t.toCharArray();
        
        //longueur du texte
        int lt = texte.length;
        //longueur de la variable
        int c = cles.length();
        //J'initialise la chaine de texte crypter
        String texteCrypter = "";

        //copie de chaque longueur
        a = lt;
        int b = c;

        int e = 0, d = 0;

        //Je determine combien de longueur de cles contient le texte et le stock dans $e
        //Verifions d'abord si la longueur du texte est plus petite aue celle de la cles
        //si c'est le cas $e vaut 1 si non cette boucle se charge de determiner sa valeur
        if(a < c) {
                e = 1;
        }else {
                while((a - b) >= 0) {
                        a = a - b;
                        e++;
                        if((a < c) && (a != 0)) {
                                e++;
                        }
                }
        }

        b = 0;
        
        //initialisation d'un tableau associatif qui va contenir des minis tableaux
        // ayant la meme longueur que la cles
        char[][] assoc1 = new char[e][c];

        /*Cette boucle remplie le tableau associatif $assoc d'une suite de tableau
         de texte en clair ayant la meme longueur que la cles */
        for(int i = 0; i < (c * e); i++) {
                if(b >= c) {
                        b = 0;
                        d++;

                        if(i > (lt - 1)) {
                                assoc1[d][b] = '_';
                        }else {
                                assoc1[d][b] = texte[i];
                        }

                        b++;
                }else {
                        if(i > (lt - 1)) {
                                assoc1[d][b] = '_';
                        }else {
                                assoc1[d][b] = texte[i];
                        }

                        b++;
                }
        }
        d++;
        
        //rangement du tableau par bloc et par caractere du plus grand au plus petit
        c = cles.length();
        char[] Tcle = new char[c];
        Tcle = cles.toCharArray();
        int ind;
        char temp;

        for(int i = 0; i < c - 1; i++) {
                ind = i;
                for(int j = i + 1; j < c; j++) {
                        if(Tcle[ind] >= Tcle[j]) {
                                ind = j;
                        }
                }

                temp = Tcle[i];
                Tcle[i] = Tcle[ind];
                Tcle[ind] = temp;

                for(b = 0; b < d; b++) {
                        temp = assoc1[b][i];
                        assoc1[b][i] = assoc1[b][ind];
                        assoc1[b][ind] = temp;
                }
        }

        //transformons le tableau multidimentionnel en tableau de chaine de caractere
        char[] TtexteCrypter = new char[e * c];
        k = 0;

        for(int i = 0; i < e; i++) {
                for(int j = 0; j < c; j++) {
                        TtexteCrypter[k] = assoc1[i][j];
                        k++;
                }
        }
        
//        String Tc = "";
//	for(int i = 0; i < TtexteCrypter.length; i++){
//		Tc = Tc + TtexteCrypter[i];
//	}
        
	//concatenons les caracteres du tableau TtexteCrypter tout en inserant les espaces dans la chaine de caractere
        String copie = "";
        ind = 0;
        int i = 0, j = 0;

        for(i = 0; i < TtexteCrypter.length; i++) {
                for( k = 0; k < nbreSpace; k++) {
                        if(i == indice[k]) {
                                ind++;
                        }
                }

                if(ind > 0) {
                        copie = copie + ' ';
                }
                copie = copie + TtexteCrypter[i];
                ind = 0;
        }
        
        //retour du texte crypter
        return copie;
    }
    
    public String getTexteCrypter2(){
        //reception du texte a crypter et de la cles de cryptage
        String atexte = this.text;
        String cles = this.cle;
        /*
        |--------------------------------------------------------------------------
        | Algorithme de transposition
        |--------------------------------------------------------------------------
        |
        | /*--- P - o - c - e - d - e - r ---/*
        |
        | 1- Demande et Recuperation du texte à crypter ainsi que de la cles de cryptage
        | 2- Suppression des espaces que contient le texte
        | 3- Création et Remplissage d'un tableau associatif $assoc d'une suite de tableau de texte en clair ayant la meme longueur que la cles 
        | 4- Je range le tableau assosiatif par bloc et par ordre croissant de caractere de cles
        | 5- Transformation du tableau associatif final en haine de caratere
        | 6- Affichage de la chaine de caratere crypter
        |
        */
        //transformation du texte a crypter en majuscule
        atexte = atexte.toUpperCase();

        //transformation de la cles de cryptage en majuscule
        cles = cles.toUpperCase();

        //retirons les espaces dans le texte
        char[] texte = new char[atexte.length()];
        texte = atexte.toCharArray();
        String t = "";
        char space;
        int k = 0, a = 0, nbreSpace = 0;
        
        //determinons le nombre d'espace contenue dans le texte
        for(int i = 0; i < texte.length; i++) {
                space = texte[i];
                if(space == ' ') {
                        nbreSpace++;
                }
        }
        
        //initialisation du tableau qui contiendra les indices de chaque espace
        int[] indice = new int[nbreSpace];
        
        //cette boucle suprime les espaces dans le texte
        for(int i = 0; i < texte.length; i++) {
                space = texte[i];
                if(space == ' ') {
                        indice[a] = i;
                        a++;
                        for(int j = i; j < texte.length - 1; j++) {
                                texte[j] = texte[j + 1];
                        }
                }

                switch(texte[i]){
                    case 'à':
                            texte[i] = 'a';
                            break;
                    case 'â':
                            texte[i] = 'a';
                            break;
                    case 'é':
                            texte[i] = 'e';
                            break;
                    case 'è':
                            texte[i] = 'e';
                            break;
                    case 'ê':
                            texte[i] = 'e';
                            break;
                    case 'ç':
                            texte[i] = 'c';
                            break;
                    case 'î':
                            texte[i] = 'i';
                            break;
                    default: ;
                }
        }
        
        //cette boucle concatene les caractere du tableau dans une chaine de caractere "t"
        for(int b = 0; b < texte.length -a; b++) {
                t = t + texte[b];
        }

        //transformation de la chaine de caractere en tableau de caractere
        texte = t.toCharArray();
        
        //longueur du texte
        int lt = texte.length;
        //longueur de la variable
        int c = cles.length();
        //J'initialise la chaine de texte crypter
        String texteCrypter = "";

        //copie de chaque longueur
        a = lt;
        int b = c;

        int e = 0, d = 0;

        //Je determine combien de longueur de cles contient le texte et le stock dans $e
        //Verifions d'abord si la longueur du texte est plus petite aue celle de la cles
        //si c'est le cas $e vaut 1 si non cette boucle se charge de determiner sa valeur
        if(a < c) {
                e = 1;
        }else {
                while((a - b) >= 0) {
                        a = a - b;
                        e++;
                        if((a < c) && (a != 0)) {
                                e++;
                        }
                }
        }

        b = 0;
        
        //initialisation d'un tableau associatif qui va contenir des minis tableaux
        // ayant la meme longueur que la cles
        char[][] assoc1 = new char[e][c];

        /*Cette boucle remplie le tableau associatif $assoc d'une suite de tableau
         de texte en clair ayant la meme longueur que la cles */
        for(int i = 0; i < (c * e); i++) {
                if(b >= c) {
                        b = 0;
                        d++;

                        if(i > (lt - 1)) {
                                assoc1[d][b] = '_';
                        }else {
                                assoc1[d][b] = texte[i];
                        }

                        b++;
                }else {
                        if(i > (lt - 1)) {
                                assoc1[d][b] = '_';
                        }else {
                                assoc1[d][b] = texte[i];
                        }

                        b++;
                }
        }
        d++;
        
        //rangement du tableau par bloc et par caractere du plus grand au plus petit
        c = cles.length();
        char[] Tcle = new char[c];
        Tcle = cles.toCharArray();
        int ind;
        char temp;

        for(int i = 0; i < c - 1; i++) {
                ind = i;
                for(int j = i + 1; j < c; j++) {
                        if(Tcle[ind] >= Tcle[j]) {
                                ind = j;
                        }
                }

                temp = Tcle[i];
                Tcle[i] = Tcle[ind];
                Tcle[ind] = temp;

                for(b = 0; b < d; b++) {
                        temp = assoc1[b][i];
                        assoc1[b][i] = assoc1[b][ind];
                        assoc1[b][ind] = temp;
                }
        }

        //transformons le tableau multidimentionnel en tableau de chaine de caractere
        char[] TtexteCrypter = new char[e * c];
        k = 0;

        for(int i = 0; i < e; i++) {
                for(int j = 0; j < c; j++) {
                        TtexteCrypter[k] = assoc1[i][j];
                        k++;
                }
        }
        
        String Tc = "";
        a = 1;
	for(int i = 0; i < TtexteCrypter.length; i++){
            
		if(i == c * a){
                    Tc = Tc + ' ';
                    a++;
                }
                Tc = Tc + TtexteCrypter[i];
	}
        
        return Tc;
    }
}
