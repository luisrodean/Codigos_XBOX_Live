package belu.www.codigosxboxlive;

import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luis on 19/12/2014.
 */

public class Frag_agregar extends Fragment {

    private Context contexto;
    private EditText txtCodigo;
    private Button btnGuardar;
    private Button btnCancelar;
    private String Codigo;

    public Frag_agregar(Context contexto){
        this.contexto = contexto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View vista = inflater.inflate(R.layout.frag_agregar,container,false);
        if(vista != null) {
            txtCodigo = (EditText) vista.findViewById(R.id.txtCodigoNuevo);
            btnGuardar = (Button) vista.findViewById(R.id.btnGuardar);
            btnCancelar = (Button) vista.findViewById(R.id.btnCancelar);
        }
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Codigo = txtCodigo.getText().toString();
                // Validar un codigo ej. GWWPX-Y32K6-XM8QM-HMV7J-9V68F
                if(validarCodigo(Codigo) == true) {
                    BDCodigos BD = new BDCodigos(contexto, "Codigos.db", null, 1);
                    BD.insertar(Codigo, 1, Fecha());
                    Toast.makeText(contexto, "Codigo Guardado", Toast.LENGTH_LONG).show();
                    Regreso();
                }else{
                    Toast.makeText(contexto,"El codigo es incorrecto Angel :P jejeje", Toast.LENGTH_LONG).show();
// Copia temporal al portapapeles para prueba
                    ClipboardManager MyClipboard = (ClipboardManager) contexto.getSystemService(contexto.CLIPBOARD_SERVICE);
                    ClipData myClip = ClipData.newPlainText("text", "W4K76-RW748-89YVX-P2B2F-34BRG");
                    MyClipboard.setPrimaryClip(myClip);
//***********
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regreso();
            }
        });
    }

    private void Regreso(){
        getActivity().finish();
        startActivity(new Intent(contexto,Primario.class));
    }

    private boolean validarCodigo(String cod){
        boolean valido = false;
        if(cod.length() == 29){
            valido = true;
        }
        return valido;
    }

    private  String Fecha(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        return today.monthDay + " / " + (today.month + 1) + " / " + today.year;
    }
}
