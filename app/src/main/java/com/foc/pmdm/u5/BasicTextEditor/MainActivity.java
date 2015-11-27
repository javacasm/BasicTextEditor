package com.foc.pmdm.u5.BasicTextEditor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText etFileName;
    EditText etContent;
    // Crear un editor de texto simple
    // Podremos guardar cualquier contenido en cualquier fichero
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFileName=(EditText)findViewById(R.id.etFileName);
        etContent=(EditText)findViewById(R.id.etContent);
    }

    // TODO: Avisar si hay cambios en el texto antes de cargar un nuevo fichero.
    // TODO: Activar el botón guardar cuando hay cambios en el texto
    // TODO: Recuperar la lista de archivos disponibles

    public void botonCargar(View v)
    {
        String strFileName=etFileName.getText().toString();

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(openFileInput(strFileName)));
            String line;
            String strContent="";
            while ((line=input.readLine())!=null)
            {
                strContent+=line;
            }
            etContent.setText(strContent);
            input.close();
        }
        catch (FileNotFoundException fio)
        {
            fio.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void botonGuardar(View v)
    {
        String strFileName=etFileName.getText().toString();

        String strContent=etContent.getText().toString();

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(strFileName, Context.MODE_PRIVATE)));
            writer.write(strContent);
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
