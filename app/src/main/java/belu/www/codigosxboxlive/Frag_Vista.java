package belu.www.codigosxboxlive;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Luis on 19/12/2014.
 */
public class Frag_Vista extends Fragment {

    private Context contexto;
    private ListView lista;
    private Lista_codigos adapter;
    private BDCodigos base;

    public Frag_Vista(Context contexto){
        this.contexto = contexto;
        adapter = new Lista_codigos(contexto);
        base = new BDCodigos(contexto,"Codigos.db",null,1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View vista = inflater.inflate(R.layout.frag_ver, container, false);
        if(vista!=null){
            lista = (ListView) vista.findViewById(R.id.listView);
        }
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogo(position);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Se eliminara el codigo " + base.Codigo(position,0))
                        .setTitle("¿Seguro?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Cierra el Dialogo
                                dialog.cancel();
                            }
                        });
                builder.show();
                return true;
            }
        });
    }

    private void dialogo(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String codigo = base.Codigo(position, 0);
        if(base.Imagen(position)!=1){
            builder.setMessage("Este codigo ya ha sido usado")
                    .setTitle("Porfavor selecciona otro :)")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SQLiteDatabase db = base.getWritableDatabase();
                            db.execSQL("DELETE FROM CodXBOX WHERE Codigo = "+ codigo +"");
                            db.close();
                            Toast.makeText(contexto, "Codigo eliminado", Toast.LENGTH_LONG).show();
                            //Cierra el Dialogo
                            dialog.cancel();
                        }
                    });
        }else {
            builder.setMessage("¿Seguro que quieres usar este codigo?")
                    .setTitle("Se usara el codigo")
                    .setPositiveButton("Usar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // copia al portapapeles el codigo
                            ClipboardManager MyClipboard = (ClipboardManager) contexto.getSystemService(contexto.CLIPBOARD_SERVICE);
                            ClipData myClip = ClipData.newPlainText("text", codigo);
                            MyClipboard.setPrimaryClip(myClip);
                            //Despliegua mensaje
                            Toast.makeText(contexto, "Codigo copiado :D", Toast.LENGTH_LONG).show();
                            //Se Modifica su valor a usado
                            base.modificar(codigo);
                            //Cierra el Dialogo
                            dialog.cancel();
                            //Mostrar pagina web en fragment
                            getFragmentManager().beginTransaction().replace(R.id.container, new Frag_page_web()).commit();
                        }
                    });
        }
        builder.show();
    }
}
