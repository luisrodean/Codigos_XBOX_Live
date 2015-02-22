package belu.www.codigosxboxlive;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Luis on 19/12/2014.
 */
public class Frag_Menu extends Fragment {

    private ImageButton A_ver;
    private ImageButton X_agregar;
    private TextView Tiempo;
    private Context contexto;
    private BDCodigos BD;

    public Frag_Menu(Context contexto){
        BD = new BDCodigos(contexto,"Codigos.db",null,1);
        SQLiteDatabase db = BD.getWritableDatabase();
        BD.onCreate(db);
        db.close();
        this.contexto = contexto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View vista = inflater.inflate(R.layout.fragment_principal,container,false);
        if(vista!=null) {
            A_ver = (ImageButton) vista.findViewById(R.id.A_ver);
            X_agregar = (ImageButton) vista.findViewById(R.id.X_agregar);
            Tiempo = (TextView) vista.findViewById(R.id.tvTiempoJuego);
        }
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        A_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo_boton("A");
            }
        });
        X_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo_boton("X");
            }
        });
        Tiempo.setText("Bienvenido, tienes "+ BD.CodigosDisponibles() +" codigos = "+ DiasJuego() + " de juego ");
    }

    private void tipo_boton(String boton){
        getActivity().finish();
        Intent select = new Intent(contexto, Secundario.class);
        select.putExtra("letra",boton);
        startActivity(select);
    }

    private String DiasJuego(){
        int horas = BD.CodigosDisponibles()*48;
        int dias = horas / 24;
        horas -=(dias * 24);
        return dias + " dias "+ horas + " hrs";
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.primario, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
