package bamr.itics.tesoem.edu.archivostxt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre;
    TextView lblmostrar;

    private final String nomarch = "datosbamr.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        lblmostrar = findViewById(R.id.lblmostrar);

        Cargarinfo();
    }

    public void MGrabar(View v){
        MAnejoArchivoTXT controlador = new MAnejoArchivoTXT();
        String Texto = "";
        try {
            Texto=txtnombre.getText().toString();
            controlador.agregar(Texto,TextoCompleto);
            TextoCompleto = controlador.getContenido();
            if (controlador.grabar(TextoCompleto,this,nomarch)){
                Toast.makeText(this,"Se grabo correctamente...",Toast.LENGTH_SHORT).show();
                Cargarinfo();
            }else{
                Toast.makeText(this,"No se pudo grabar correctamente...",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void MLeer(View v){
        Cargarinfo();
    }

    private void Cargarinfo() {
        MAnejoArchivoTXT objmanarch = new MAnejoArchivoTXT();
        if (objmanarch.verificar(this, nomarch)) {
            Toast.makeText(this, "Existe el archivo...", Toast.LENGTH_SHORT).show();
            if (objmanarch.leer(    this, nomarch)) {
                TextoCompleto = objmanarch.getContenido();
                String cadena = "";
                for (String micadena : TextoCompleto) {
                    cadena += micadena + '\n';
                }
                lblmostrar.setText(cadena);
            }
        } else {
            Toast.makeText(this, "No Existe el Archivo", Toast.LENGTH_LONG).show();
        }
    }
}
