package de.mide.vektorgrafiken;

import java.util.ArrayList;

import android.app.ActionBar.OnNavigationListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Demo für die mit API-Level 21 (Android 5.0) eingeführten <i>VectorDrawables</i>.
 * Siehe die SVG-Dateien unter <i>"res/drawable"</i>.
 * <br><br>
 *
 * Verwendet auch die mit API-Level 11 eingeführte <i>ActionBar</i>.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends AppCompatActivity  {

    /** Tag für Log-Messages der ganzen App. */
    public static final String TAG4LOGGING = "VektorGrafikDemo";

    /** UI-Element zur Darstellung der Vektor-Grafiken (VectorDrawables). */
    protected ImageView _imageView = null;


    /**
     * Lifecycle-Methode, ruft Konfiguration der ActionBar auf.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _imageView = findViewById(R.id.imageview_vektorgrafik);

        actionBarKonfigurieren();
    }


    /**
     * Konfiguration der {@link ActionBar}.
     * Die ActionBar wurde mit Android 3.0 (API-Level 11) eingeführt.
     * Sie ist in allen Activities verfügbar, die auf dem <i>Theme.Holo</i> basieren
     * und als <i>targetSDK</i> mindestens das API-Level 11 haben.
     * <br><br>
     *
     * Die Konfiguration der DropDown-Liste für die Navigation ist in eine andere
     * Methode in dieser Klasse ausgelagert.
     * <br><br>
     *
     * Die Menu-Items für die ActionBar werden in der überschriebenen Methode
     * {@link #onCreateOptionsMenu(Menu)} hinzugefügt.
     */
    protected void actionBarKonfigurieren() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {

            Toast.makeText(this, "Keine ActionBar vorhanden.", Toast.LENGTH_LONG).show();
            return;
        }

        // Titel und Untertitel setzen
        actionBar.setTitle   ( "Vektor-Grafiken"         );
        actionBar.setSubtitle( "Demo für VectorDrawable" );

        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_HOME); // Logo/Icon ausblenden
        //actionBar.hide();
    }


    /**
     * Hinzufügen von Menu-Items zur ActionBar.
     * Der zugehörige Event-Handler ist die Methode {{@link #onOptionsItemSelected(MenuItem)}.
     *
     * @param menu  Menü-Objekt
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu_items, menu); // Menü-Definition aus Ressourcen-Datei "aufblasen"

        return super.onCreateOptionsMenu(menu);
    }


    /**
     * Event-Handler für Menu-Items in ActionBar.
     * Einrichtung dieser Menu-Items durch Überschreiben von Methode
     * {@link #onCreateOptionsMenu(Menu)}.
     *
     * @param item  Menu-Item, welches gerade ein Event ausgelöst hat.
     *
     * @return Es wird genau dann <i>true</i> zurückgegeben, wenn wir
     *         in dieser Methode das Ereignis verarbeiten konnten.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_kreis:
                _imageView.setImageResource(R.drawable.vektorgrafik_kreis);
                return true;

            case R.id.action_diagonale:
                _imageView.setImageResource(R.drawable.vektorgrafik_linien);
                return true;

            case R.id.action_dreieck:
                _imageView.setImageResource(R.drawable.vektorgrafik_dreieck);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Methode öffnet ein Dialog-Objekt zur Anzeige von Informationen über
     * die App, wenn der Info-Button in der ActionBar betötigt wird.
     */
    protected void zeigeInfoDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Info-Dialog");
        dialogBuilder.setMessage(R.string.info_dialog_text);

        // Button des Dialog konfigurieren
        OnClickListener weiterButtonListener = new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        dialogBuilder.setPositiveButton( "Weiter", weiterButtonListener );
        dialogBuilder.setCancelable(false); // es wird kein Cancel-Button benötigt

        // Erzeugen und Anzeigen des Dialogs
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

};
