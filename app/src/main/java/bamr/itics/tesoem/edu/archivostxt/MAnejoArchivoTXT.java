package bamr.itics.tesoem.edu.archivostxt;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MAnejoArchivoTXT {
    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    public void  agregar(String dato,ArrayList<String> contenido){
        TextoCompleto=contenido;
        TextoCompleto.add(dato);
    }

    public boolean grabar(ArrayList<String> dato, Context contexto, String nomarch){
        boolean estado = true;
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(contexto.openFileOutput(nomarch, Activity.MODE_PRIVATE));
            for (String Texto : dato)
                archivo.write(Texto+"\n");
            archivo.flush();
            archivo.close();
        } catch (Exception ex){
            estado = false;
        }
        return estado;
    }

    public boolean leer(Context context,String nomarch) {
        ArrayList<String> textocompleto = new ArrayList<String>();
        try {
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(nomarch));
            BufferedReader br = new BufferedReader(archivo);
            String cadena = br.readLine();
            while (cadena != null){
                textocompleto.add(cadena);
                cadena = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        TextoCompleto = textocompleto;
        return true;
    }

    public boolean verificar(Context context, String nomarch){
        String[] archivos = context.fileList();
        for (String archivo: archivos){
            if (archivo.equals(nomarch)) return true;
        }
        return false;
    }

    public ArrayList<String> getContenido(){
        return TextoCompleto;
    }

}
