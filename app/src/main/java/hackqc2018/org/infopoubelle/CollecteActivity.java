package hackqc2018.org.infopoubelle;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.List;

public class CollecteActivity extends AppCompatActivity {

    ArrayList<String> autres = new ArrayList<>();
    ArrayList<String> compost = new ArrayList<>();
    ArrayList<String> numberlist = new ArrayList<>();

    ArrayList<String> dechets = new ArrayList<>();
    ArrayList<String> ecocentre = new ArrayList<>();
    ArrayList<String> recuperation = new ArrayList<>();
    Spinner dropdown;
    JSONArray jsonArrayLaval;
    JSONArray jsonArraySherbrooke;

    String sLaval = "{\"Toto\":[\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Bois\",\"description\":\"Copeaux de bois\",\"instructions-speciales\":\"Copeaux de bois d'au moins 1 centimètre d'épaisseur\"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Bois\",\"description\":\"Planches, madriers, bois traité, poutres et dormants de moins de 2 m\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Bois\",\"description\":\"Souches, troncs d'arbres et branches provenant de Laval et de moins de 2 m de long\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Baignoires, lavabos, cuves, douches et cuves de toilettes\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Bardeaux de toiture\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Béton, béton armé, mortier et ciment durcis en morceaux\",\"instructions-speciales\":\"Moins de 30 cm de long\"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Briques, blocs et pièces de maçonnerie et pavage durci\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Céramique\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Isolant\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Plâtras (gypse, etc.) de moins de 2 m de long\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Tapis et prélart roulés et solidement ficelés de moins de 2 m de long\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Tuyaux, tubes et drains de moins de 2,4 m de long\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matériaux de construction\",\"description\":\"Vitre \",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matières recyclables\",\"description\":\"Contenants divers : plastique rigide des familles 1 à 5, verre clair et de couleur\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Matières recyclables\",\"description\":\"Papier et carton\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Métal et aluminium\",\"description\":\"Acier d'armature, câbles et poutres d'acier\",\"instructions-speciales\":\"Moins de 2,4 m de long\"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Métal et aluminium\",\"description\":\"Fer, fonte, acier et aluminium\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Métal et aluminium\",\"description\":\"Laveuses, poêles et sécheuses\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Métal et aluminium\",\"description\":\"Réservoirs d'eau chaude\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Aires de réception de matériaux secs\",\"categorie\":\"Terre et pierre\",\"description\":\"Terre de jardin, pierre, sable et tourbe de provenance résidentielle. \",\"instructions-speciales\":\"Ces matières sont acceptées uniquement au 4026, boulevard Dagenais Ouest\"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Autres\",\"description\":\"Filtres à café \",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Autres\",\"description\":\"Litière d'animaux\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Autres\",\"description\":\"Cartons souillés d'aliments : boîtes à pizza, vaisselle en carton, etc.\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Autres\",\"description\":\"Essuie-tout, mouchoirs\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Autres\",\"description\":\"Cendres froides (refroidit pendant 7 jours)\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Autres\",\"description\":\"Cure-dents et bâtonnets en bois\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Autres\",\"description\":\"Cheveux et poils\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Comestible\",\"description\":\"Fruits et légumes entiers ou leurs restes\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Comestible\",\"description\":\"Grains de café et sachets de thé\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Comestible\",\"description\":\"Pain, riz, céréales\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Comestible\",\"description\":\"Viandes, poissons, fruits de mer, volailles, os et coquilles de crustacés\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Comestible\",\"description\":\"Produits laitiers solides : fromage, beurre, etc.\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Végétale\",\"description\":\"Gazon, feuilles mortes et terre à jardin pour un maximum de la moitié du bac vert roulant\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Végétale\",\"description\":\"Aiguilles et cônes de conifères \",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Végétale\",\"description\":\"Fleurs, plantes, mauvaises herbes et résidus de jardinage\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Végétale\",\"description\":\"Foin et paille\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Végétale\",\"description\":\"Écorces, copeaux, bran de scie et racines\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières organiques\",\"categorie\":\"Végétale\",\"description\":\"Branches d'arbres  d'un maximum de 1,2 m et de 25 kg par section de branches\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Autres\",\"description\":\"Capsules de café Nespresso\",\"instructions-speciales\":\"Les capsules de café NESPRESSO sont acceptées dans le bac de recyclage, lorsqu'elles sont déposées dans le sac vert distribué à cet effet par l'entreprise.\"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Métal et aluminium\",\"description\":\"Papier et contenants d'aluminium\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Métal et aluminium\",\"description\":\"Bouteilles et canettes d'aluminium\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Métal et aluminium\",\"description\":\"Boîtes de conserve\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Métal et aluminium\",\"description\":\"Bouchons et couvercles\",\"instructions-speciales\":null},\n" +
            "  {\"nom-collecte\":\" \",\"categorie\":null,\"description\":null,\"instructions-speciales\":null},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Papier et carton\",\"description\":\"Journaux, circulaires et revues\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Papier et carton\",\"description\":\"Feuilles, enveloppes et sacs de papier\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Papier et carton\",\"description\":\"Livres et annuaires téléphoniques\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Papier et carton\",\"description\":\"Rouleaux et boîtes de carton\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Papier et carton\",\"description\":\"Boîtes d'œufs\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Papier et carton\",\"description\":\"Cartons de lait et carton de jus et leur bouchon (bien rincés)\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Plastique\",\"description\":\"Contenants de type Tetra PakMD (carton de soupe de soupe prêt-à-manger, carton de jus vendus sur les tablettes, etc.)\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Plastique\",\"description\":\"Plastiques de catégories 1, 2, 3, 4, 5, 7\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Plastique\",\"description\":\"Sacs de plastique\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Plastique\",\"description\":\"Sacs et pellicules d'emballage\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Plastique\",\"description\":\"Bouchons et couvercles\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Plastique\",\"description\":\"Bouteilles (cosmétiques, produits alimentaires, etc.)\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des matières recyclables\",\"categorie\":\"Verre\",\"description\":\"Bouteilles et pots de toute couleur\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des ordures ménagères et autres matières résiduelles\",\"categorie\":\"déchets\",\"description\":\"ordures ménagères\",\"instructions-speciales\":\"Le poids de la poubelle, incluant son contenu, ne doit pas dépasser 25 kg (55 lbs)\"},\n" +
            "  {\"nom-collecte\":\"Collecte des ordures ménagères et autres matières résiduelles\",\"categorie\":\"Matériaux de construction\",\"description\":\"Matériaux de construction\",\"instructions-speciales\":\"Poids limite par collecte est de 100 kg (220 lbs), les matériaux doivent être attachés en paquets ou placés à l'intérieur de contenants étanches, sans clous ni vis. Chaque paquet ne doit pas peser plus de 25 kg (55 lbs) ni dépasser 1,80 m de long\"},\n" +
            "  {\"nom-collecte\":\"Collecte des ordures ménagères et autres matières résiduelles\",\"categorie\":\"Résidus de jardin\",\"description\":\"Terre\",\"instructions-speciales\":\"Maximum de 25 kg (55 lbs) par collecte\"},\n" +
            "  {\"nom-collecte\":\"Collecte des ordures ménagères et autres matières résiduelles\",\"categorie\":\"Résidus de jardin\",\"description\":\"Pierre et tourbe\",\"instructions-speciales\":\"Maximum de 10 kg (22 lbs) par collecte\"},\n" +
            "  {\"nom-collecte\":\"Collecte des ordures ménagères et autres matières résiduelles\",\"categorie\":\"Résidus de jardin\",\"description\":\"Souches, branches et troncs d'arbre\",\"instructions-speciales\":\"Maximum de 100 kg (220 lbs) par collecte, en paquets de 25 kg (55 lbs) et moins et ne dépassant pas 1,80 de long\"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Acides\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Aérosols\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Ampoules fluocompactes\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Bonbonnes de propane\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"Résidus TIC\",\"description\":\"Cartouches d'encre\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Colle\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Diluants et solvants\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Huiles à moteur et filtres à huile\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"Résidus TIC\",\"description\":\"Matériel informatique et électronique\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Médicaments\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Nettoyants\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Peintures, teintures, vernis\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Pesticides\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"Résidus TIC\",\"description\":\"Petits appareils électriques et électroniques\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Piles et accumulateurs (batteries d'auto)\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"Pneus\",\"description\":\"Pneus\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"RDD\",\"description\":\"Produits chimiques\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"Résidus TIC\",\"description\":\"Téléphones cellulaires\",\"instructions-speciales\":\" \"},\n" +
            "  {\"nom-collecte\":\"Collecte des résidus domestiques dangereux\",\"categorie\":\"Résidus TIC\",\"description\":\"Téléviseurs\",\"instructions-speciales\":null}]}";

    String sSherbrooke = "{\"MATIERE_RESIDUELLE\":[\n" +
            "{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Tous produits biomédicaux\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"CLSC ou pharmacie. Ex.: seringues, aiguilles, tubulures, etc.\"},{\"MUNID\": \"43027\"," +
            "\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Vêtements et Autre textiles\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Comptoirs vestimentaires, friperies\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Objets encombrants\",\"TYPE_COL\": \"S\",\"DESC_COL\": \"Collectes spéciales\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier essuie-tout\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier mouchoir\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier et carton souillés\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"Souillés d’aliments, de graisse, d’huile, de peinture, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Aliments périmés (sans emballage)\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Café moulu et filtres\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Sachets de thé\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Épis de maïs\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Friandises et desserts\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"Excepté la gomme à mâcher\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Fruits et légumes\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Matières grasses\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"OEufs et leurs coquilles\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Pâtes alimentaires\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Pains\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Céréales\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Produits laitiers\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Reste de repas\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Viande\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"Comprenant les os, la peau, les graisses et les entrailles\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Volaille\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"Comprenant les os, la peau, les graisses et les entrailles\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Poisson et fruits de mer\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"Comprenant les os, la peau, les graisses et les entrailles\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Branches et petites racines non attachées\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"D’une longueur maximale de 60 cm d’une longueur maximale de 60 cm\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Bran de scie\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Copeaux\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Écorces\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Fleurs\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Plantes et Autre résidus végétaux\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"Aiguilles de conifères, retailles de haie, mauvaises herbes, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Herbe coupée et chaume\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes de livraison de repas\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papiers essuie-mains\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Serviettes de table\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Plantes d’intérieur incluant le terreau d'empotage\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs compostables, si certifiés BNQ\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Vaisselle compostable\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"En fécule de maïs par exemple\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Cendres froides\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": \"Éteintes depuis au moins sept jours\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Cheveux\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Poils d’animaux\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Plumes\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Litières et excréments d’animaux domestiques\",\"TYPE_COL\": \"C\",\"DESC_COL\": \"Compost - Bac brun\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Autocollants plastifiés\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes de jus congelé\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Carton ciré\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Boîtes et assiettes de repas congelé, boîtes de crème glacée, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Contenants en carton enduit d’aluminium ou de plastique\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Ex.: boîtes de cacao ou d’arachides, croustilles ou chips Pringles, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier carbone\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier ciré\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Incluant les sacs de boîtes de céréales\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier photo\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Sacs ou enveloppes composés de plusieurs matières\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Mélange d’aluminium, de plastique et de papie. Ex.: sacs de croustilles ou chips, sacs de biscuits, emballages de barres tendres, de chocolat, de gomme à mâcher ou de nourriture pour animaux, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Verre en carton pour le café et Autre boissons\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Décorations électriques\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Moustiquaires\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Petit objets en métal\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Moins de 5 cm ou 2 po.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Bâches de plastique tissé\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Ballons\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Jeux gonflables\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Emballage de ballots de foin\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Filets d’oignons ou d’oranges\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Gants de latex\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Guirlandes de Noël\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Nappes en vinyle\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs de plastique souillés\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Pellicules souillées\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Sacs et poches tissés\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Ex. : poches de moulée\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Toiles de piscine\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Emballages composés de plusieurs matières\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Mélange d’aluminium, de plastique et de papie. Ex.: emballages de barres tendres, de chocolat, de gomme à mâcher ou de nourriture pour animaux, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Articles de vinyle\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Assiettes en plastique\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Ustensiles en plastique\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Balles de golf\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Balles de tennis\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\n" + "\"MUNID\": \"43027\"," +
            "\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Balles de baseball\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Cartables\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Chaises et tables de patio (PVC)\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Cintres en plastique\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Emballages moulés\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Ceux qu’on doit ouvrir avec des ciseaux pour dégager l’objet acheté\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Pailles en plastique\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Plastique d’ordinateur ou autre appareil en plastique\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sapins artificiels\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Stores\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Tubes de dentifrice\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Tuyaux d’arrosage\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Tubulure d’érablière\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Tuyaux de plomberie rigides ou souples\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Ex. : carlon, PVC, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Fibre de verre\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Miroirs\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Porcelaine\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Céramique\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Pyrex\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Tubes fluorescents\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Vaisselle\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Verre à boire\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Vitre (verre plat)\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes de carton pour aliments et repas congelés\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Bouchon de liège\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Cartons de crème glacée\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Charpie de sécheuse\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Feuilles assouplisantes\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Cire\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Coton-tiges\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Feuilles jetables de balai\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Gomme   à mâcher\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Mégots et cendred de cigarettes\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Plastique sans logo de recyclage\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Sacs d'aspiratuer\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": \"Et leurs contenu\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Sièges d'auto pour enfants\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Ustensiles\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs de plastique biodégradables\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs de plastique oxobiodégradables\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Tapis, moquette\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Matériaux isolants\",\"TYPE_COL\": \"D\",\"DESC_COL\": \"Bac pour les déchets\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Feuilles mortes\",\"TYPE_COL\": \"S\",\"DESC_COL\": \"Collectes spéciales\",\"INFO\": \"Écocentre, bac brun pour les matières Organique ou collecte spéciale des feuilles mortes\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Aérosols\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres ou collecte de résidus domestiques dangereux\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Batteries d'auto\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Cintres\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Fils\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Câbles\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Corde à linge\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Broches en métal\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Contenants de peinture\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres ou collecte de résidus domestiques dangereux\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Décapant\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres ou collecte de résidus domestiques dangereux\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Matières dangereuses\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres ou collecte de résidus domestiques dangereux\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Fils électriques\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Pièces de métal de plus de\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"2 kg (4,4 lbs), longueur min. : 5 cm (2 po), longueur max. : 60 cm (24 po).\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Aluminium\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Contenants d’huile à moteur\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Contenants de térébenthine\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Contenants d'essence\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Contenants de produits dangereux\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Contenants de plastique portant le numéro 6\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Ex.: polystyrène et styromousse\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Cordes de nylon\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Styromousse\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Ex. : barquettes de viande, emballages, résidus de construction, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Ampoules électriques\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Bois et branches\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Écocentre ou collecte spéciale de bois\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Pneaus d'automobile ou de vélo\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Sans jante\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Bois de construction\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Naturel, traité ou peint\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Miroirs\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Céramique\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Bardeaux\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Gyspse\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Portes\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Fenêtres\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Matériaux de construction\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Dimension maximale de 1,2 m sur 2,4 m (4 pi sur 8 pi).\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Toilettes\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Éviers en porcelaine,\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Pesticides\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Gravier de rue\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Pierres\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Roches\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Terre\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Non contaminée.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Tourbe\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Gazon\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Le gazon est accepté trois fois par année seulement. Ensuite, il est refusé.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Béton\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Briques\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Asphalte\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Ordinateur\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Moniteurs, tours, claviers et souris\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Imprimante\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Photocopieur\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Télécopieurs\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Téléviseurs\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Radio\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Cartouches\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Lecteurs\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Vhs. CD, DVD\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Téléphonnes\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Cellulaires\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Appareils photos\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Et leurs boîtiers\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"CD\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Et leurs boîtiers\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"DVD\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Piles\",\"TYPE_COL\": \"O\",\"DESC_COL\": \"Apporter aux écocentres\",\"INFO\": \"Couvrir les pôles à l'aide de papier isolant (comme du ruban électique).\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes d’aliments congelés (non cirés)\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Cartons à jus à intérieur d’aluminium  (Tetra Pack)\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Cartons de lait\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Cartons de jus\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Sans la paille\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Journaux\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Journaux\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Magazines\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Feuillets publicitaires\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Retirer les circulaires et les journaux du Publi-Sac\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier à lettres\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Enveloppes\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Livres\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier déchiqueté\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Regroupé dans un sac transparent et noué.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Sacs bruns\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Utilisez le bac brun pour le papier et le carton souillés.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes de carton ondulé\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Grandeur maximum : 60 cm sur 90 cm ou 2 pi sur 3 pi.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Carton plat\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Grandeur maximum : 60 cm sur 90 cm ou 2 pi sur 3 pi.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes de savon à lessive\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Carton ondulé à l’intérieur emballages de biscuits\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes à boire\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Cartons de jus en portion individuelle\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Bottins téléphoniques\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Nappes en papier non souillées\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier à lettres\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Enveloppes\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier d’emballage de papier à photocopie\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier d’emballages cadeaux (non métallique)\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier de soie\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Papier brun\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Papier d’aluminium propres\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Regrouper dans une boule compacte\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Boîtes de conserve\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Les étiquettes peuvent être laissées sur les boîtes de conserve. Rincer les contenants.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Cannettes d’aluminium\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Rincer les contenants.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Couvercles de métal\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Assiettes d’aluminium propres\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Casseroles en métal\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Grille-pain\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Sans le cordon d'alimentation électrique\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Petits appareils électriques\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Sans le cordon d'alimentation électrique\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Métal\",\"MAT_NOM\": \"Pièces de métal\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Poids max : 2 kg (4,4 lbs), longueur min. : 5 cm (2 po), longueur max. : 60 cm (24 po).\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Emballages\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Pellicules de plastique propres,…ensachés\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Ex. : emballages de fromage, de papier de toilette, Saran wrap, etc.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs d’épicerie\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Ensacher tous les Plastique dans un sac en plastique noué\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs de magasinage\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Ensacher tous les Plastique dans un sac en plastique noué\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs de lait\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Ensacher tous les Plastique dans un sac en plastique noué\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs de pain\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Ensacher tous les Plastique dans un sac en plastique noué\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Nappes en plastique à usage unique\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Nettoyées (mais pas celles en vinyle)\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Papier bulle pour emballage\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Ensacher tous les Plastique dans un sac en plastique noué\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Polythène propre\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Exempt de peinture ou d’huile\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Sacs à sandwich\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Ensacher tous les Plastique dans un sac en plastique noué\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Jouets de plastique sans aucune pièce de métal\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Tous les articles de plastique\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Portant à l’intérieur d’un triangle,  les chiffres 1 à 7 (sauf le numéro 6)\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Contenants de produits alimentaires\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Portant à l’intérieur d’un triangle,  les chiffres 1 à 7 (sauf le numéro 6)\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Contenants de produits ménagers\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Portant à l’intérieur d’un triangle,  les chiffres 1 à 7 (sauf le numéro 6)\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Contenants de produits cosmétiques\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Portant à l’intérieur d’un triangle,  les chiffres 1 à 7 (sauf le numéro 6)\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Bouteilles en plastique\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Laisser les bouchons en plastique sur les bouteilles\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Couvercles en plastique\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Plastique\",\"MAT_NOM\": \"Seaux en plastique propres\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": null},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Bouteilles en verre clair ou de couleur\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Avec ou sans étiquettes. Récupérez le couvercle séparément. Rincer les contenants.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Pots\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Avec ou sans étiquettes. Récupérez le couvercle séparément. Rincer les contenants.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Verre\",\"MAT_NOM\": \"Flacons\",\"TYPE_COL\": \"R\",\"DESC_COL\": \"Bac de récupération\",\"INFO\": \"Avec ou sans étiquettes. Récupérez le couvercle séparément. Rincer les contenants.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Sapins naturels\",\"TYPE_COL\": \"S\",\"DESC_COL\": \"Collectes spéciales\",\"INFO\": \"Ne couvrez pas votre sapin d’une housse de plastique. Enlevez toutes les décorations.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Papier et carton\",\"MAT_NOM\": \"Boîtes de carton de déménagement\",\"TYPE_COL\": \"S\",\"DESC_COL\": \"Collectes spéciales\",\"INFO\": \"Ou écocentre. Défaites les boîtes et empilez-les à côté des bacs roulants. Respectez les dimensions maximales de 90 cm sur 90 cm (3 pi sur 3 pi).\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Animaux morts\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Communiquez avec Cremanimo, au 819 347-3434 ou avec Récupération Maillé, au 819 847-4907.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Médicaments périmés\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Rapporter à la pharmacie\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Munitions\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Rendez-vous à la Sûreté du Québec.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Pneus avec jantes\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Rendez-vous dans un garage.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Pneus de véhicules Autre qu’automobiles\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Rendez-vous dans un garage spécialisé.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Autre\",\"MAT_NOM\": \"Résidus dangereux d’origine commerciale ou industrielle\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Communiquez avec : Véolia Environnement, au 819 822-1820 ou FEC Technologie, au 819 843-3550 ou J. Beauclair Métal Environnement, au 819 820-5078.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\",\"MAT_NOM\": \"Tailles de haies de cèdres en grande quantité\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Communiquez avec Récupérateur de tailles de cèdre, au 819 875-3027.\"},{\"MUNID\": \"43027\",\"MAT_CAT\": \"Organique\"," +
            "\"MAT_NOM\": \"Terre contaminée\",\"TYPE_COL\": \"A\",\"DESC_COL\": \"Autres - Matières spécifiques\",\"INFO\": \"Communiquez avec GSI Environnement, au 819 829-0101.\"}]}";

    JSONArray jsonArrayTemp;
    String ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collecte_activity);


        try {
            JSONObject jsonObject = (JSONObject) new JSONTokener(sLaval).nextValue();
            jsonArrayLaval = jsonObject.getJSONArray("Toto");
            JSONObject jsonObject2 = (JSONObject) new JSONTokener(sSherbrooke).nextValue();
            jsonArraySherbrooke = jsonObject2.getJSONArray("MATIERE_RESIDUELLE");
        }catch(JSONException e){
            e.printStackTrace();
        }
/*
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://58ddb3f2.ngrok.io";


        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try{
                            JSONObject jsonObject = (JSONObject)new JSONTokener(response).nextValue();
                            jsonArray = jsonObject.getJSONArray("Toto");
                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                        //jsonArray =  response;
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "Error");
                    }
                });


        // Add the request to the RequestQueue.
        queue.add(request);*/

       // initLaval();
        dropdown = findViewById(R.id.droplist);
        List<String> list = new ArrayList<String>();
        list.add("Laval");
        list.add("Sherbrooke");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);

        Button btnRecup = findViewById(R.id.btnRecup);
        btnRecup.setVisibility(View.INVISIBLE);
        Button btnCompost = findViewById(R.id.btnCompost);
        btnCompost.setVisibility(View.INVISIBLE);
        Button btnEco = findViewById(R.id.btnEco);
        btnEco.setVisibility(View.INVISIBLE);
        Button btnDechets = findViewById(R.id.btnDechets);
        btnDechets.setVisibility(View.INVISIBLE);
        Button btnAutre = findViewById(R.id.btnAutre);
        btnAutre.setVisibility(View.INVISIBLE);
    }

    public void OnClickOk(View view){
        TableLayout stk = (TableLayout) findViewById(R.id.tabInfo);
        stk.removeAllViewsInLayout();
        dropdown = findViewById(R.id.droplist);
        ville = dropdown.getSelectedItem().toString();
        Button btnRecup = findViewById(R.id.btnRecup);
        btnRecup.setVisibility(View.VISIBLE);
        Button btnCompost = findViewById(R.id.btnCompost);
        btnCompost.setVisibility(View.VISIBLE);
        Button btnEco = findViewById(R.id.btnEco);
        btnEco.setVisibility(View.VISIBLE);
        Button btnDechets = findViewById(R.id.btnDechets);
        btnDechets.setVisibility(View.VISIBLE);
        Button btnAutre = findViewById(R.id.btnAutre);
        btnAutre.setVisibility(View.VISIBLE);
    }

    public void OnClickRecycle(View view){
        try{
            jsonArrayTemp = new JSONArray();
            if(ville.equals("Laval")){
                for (int i = 0; i < jsonArrayLaval.length(); i++){
                    if(jsonArrayLaval.getJSONObject(i).get("nom-collecte").toString().contains("recyclables")){
                        jsonArrayTemp.put(jsonArrayLaval.getJSONObject(i));
                    }
                }
                initLaval();
            }
            else {
                for (int i = 0; i < jsonArraySherbrooke.length(); i++){
                    if(jsonArraySherbrooke.getJSONObject(i).get("TYPE_COL").toString().contains("R")){
                        jsonArrayTemp.put(jsonArraySherbrooke.getJSONObject(i));
                    }
                }
                initSherbrooke();
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    public void OnClickCompost(View view){
        try{
            jsonArrayTemp = new JSONArray();
            if(ville.equals("Laval")){
                for (int i = 0; i < jsonArrayLaval.length(); i++){
                    if(jsonArrayLaval.getJSONObject(i).get("nom-collecte").toString().contains("organiques")){
                        jsonArrayTemp.put(jsonArrayLaval.getJSONObject(i));
                    }
                }
                initLaval();
            }
            else {
                for (int i = 0; i < jsonArraySherbrooke.length(); i++){
                    if(jsonArraySherbrooke.getJSONObject(i).get("TYPE_COL").toString().contains("C")){
                        jsonArrayTemp.put(jsonArraySherbrooke.getJSONObject(i));
                    }
                }
                initSherbrooke();
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    public void OnClickAutres(View view){
        try{
            jsonArrayTemp = new JSONArray();
            if(ville.equals("Laval")){
                /*for (int i = 0; i < jsonArrayLaval.length(); i++){
                    if(jsonArrayLaval.getJSONObject(i).get("nom-collecte").toString().contains("matériaux")||
                            jsonArrayLaval.getJSONObject(i).get("nom-collecte").toString().contains("dangereux")){
                        jsonArrayTemp.put(jsonArrayLaval.getJSONObject(i));
                    }
                }
                initLaval();*/
            }
            else {
                for (int i = 0; i < jsonArraySherbrooke.length(); i++){
                    if(jsonArraySherbrooke.getJSONObject(i).get("TYPE_COL").toString().contains("A")){
                        jsonArrayTemp.put(jsonArraySherbrooke.getJSONObject(i));
                    }
                }
                initSherbrooke();
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

    }
    public void OnClickDechets(View view){
        try{
            jsonArrayTemp = new JSONArray();
            if(ville.equals("Laval")){
                for (int i = 0; i < jsonArrayLaval.length(); i++){
                    if(jsonArrayLaval.getJSONObject(i).get("nom-collecte").toString().contains("ordures")){
                        jsonArrayTemp.put(jsonArrayLaval.getJSONObject(i));
                    }
                }
                initLaval();
            }
            else {
                for (int i = 0; i < jsonArraySherbrooke.length(); i++){
                    if(jsonArraySherbrooke.getJSONObject(i).get("TYPE_COL").toString().contains("D")){
                        jsonArrayTemp.put(jsonArraySherbrooke.getJSONObject(i));
                    }
                }
                initSherbrooke();
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

    }
    public void OnClickEcocentre(View view){
        try{
            jsonArrayTemp = new JSONArray();
            if(ville.equals("Laval")){
                for (int i = 0; i < jsonArrayLaval.length(); i++){
                    if(jsonArrayLaval.getJSONObject(i).get("nom-collecte").toString().contains("matériaux")||
                            jsonArrayLaval.getJSONObject(i).get("nom-collecte").toString().contains("dangereux")){
                        jsonArrayTemp.put(jsonArrayLaval.getJSONObject(i));
                    }
                }
                initLaval();
            }
            else {
                for (int i = 0; i < jsonArraySherbrooke.length(); i++){
                    if(jsonArraySherbrooke.getJSONObject(i).get("TYPE_COL").toString().contains("O")){
                        jsonArrayTemp.put(jsonArraySherbrooke.getJSONObject(i));
                    }
                }
                initSherbrooke();
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }


    public void initLaval() {

        try{
            TableLayout stk = (TableLayout) findViewById(R.id.tabInfo);
            stk.removeAllViewsInLayout();

            TableRow tbrow0 = new TableRow(this);
            TextView tv0 = new TextView(this);
            tv0.setText(" Catégorie ");
            tv0.setTextColor(Color.BLACK);
            tv0.setTypeface(null, Typeface.BOLD);
            tv0.setGravity(Gravity.CENTER);
            tbrow0.addView(tv0);
            TextView tv1 = new TextView(this);
            tv1.setText(" Description ");
            tv1.setGravity(Gravity.CENTER);
            tv1.setTypeface(null, Typeface.BOLD);
            tv1.setTextColor(Color.BLACK);
            tbrow0.addView(tv1);
            TextView tv2 = new TextView(this);
            tv2.setText(" Instructions ");
            tv2.setTextColor(Color.BLACK);
            tv2.setGravity(Gravity.CENTER);
            tv2.setTypeface(null, Typeface.BOLD);
            tbrow0.addView(tv2);
            stk.addView(tbrow0);
            for (int i = 0; i < jsonArrayTemp.length(); i++) {
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(jsonArrayTemp.getJSONObject(i).getString("categorie"));
                t1v.setTextColor(Color.BLACK);
                t1v.setWidth(350);
                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                TextView t2v = new TextView(this);
                t2v.setText(jsonArrayTemp.getJSONObject(i).getString("description"));
                t2v.setTextColor(Color.BLACK);
                t2v.setWidth(350);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);
                TextView t3v = new TextView(this);
                t3v.setText(jsonArrayTemp.getJSONObject(i).getString("instructions-speciales"));
                t3v.setTextColor(Color.BLACK);
                t3v.setGravity(Gravity.CENTER);
                t3v.setWidth(350);
                tbrow.addView(t3v);
                stk.addView(tbrow);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void initSherbrooke() {

        try{
            TableLayout stk = (TableLayout) findViewById(R.id.tabInfo);
            stk.removeAllViewsInLayout();
            TableRow tbrow0 = new TableRow(this);
            TextView tv0 = new TextView(this);
            tv0.setText(" Catégorie ");
            tv0.setTextColor(Color.BLACK);
            tv0.setTypeface(null, Typeface.BOLD);
            tv0.setGravity(Gravity.CENTER);
            tbrow0.addView(tv0);
            TextView tv1 = new TextView(this);
            tv1.setText(" Description ");
            tv1.setGravity(Gravity.CENTER);
            tv1.setTypeface(null, Typeface.BOLD);
            tv1.setTextColor(Color.BLACK);
            tbrow0.addView(tv1);
            TextView tv2 = new TextView(this);
            tv2.setText(" Instructions ");
            tv2.setTextColor(Color.BLACK);
            tv2.setGravity(Gravity.CENTER);
            tv2.setTypeface(null, Typeface.BOLD);
            tbrow0.addView(tv2);
            stk.addView(tbrow0);
            for (int i = 0; i < jsonArrayTemp.length(); i++) {
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(jsonArrayTemp.getJSONObject(i).getString("MAT_CAT"));
                t1v.setTextColor(Color.BLACK);
                t1v.setWidth(350);
                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                TextView t2v = new TextView(this);
                t2v.setText(jsonArrayTemp.getJSONObject(i).getString("MAT_NOM"));
                t2v.setTextColor(Color.BLACK);
                t2v.setWidth(350);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);
                TextView t3v = new TextView(this);
                t3v.setText(jsonArrayTemp.getJSONObject(i).getString("INFO"));
                t3v.setTextColor(Color.BLACK);
                t3v.setGravity(Gravity.CENTER);
                t3v.setWidth(350);
                tbrow.addView(t3v);
                stk.addView(tbrow);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
