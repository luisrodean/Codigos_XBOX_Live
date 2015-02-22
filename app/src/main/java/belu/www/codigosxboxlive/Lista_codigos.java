package belu.www.codigosxboxlive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by Luis on 25/12/2014.
 */
public class Lista_codigos extends BaseAdapter {

    private Context contexto;
    private BDCodigos base;
    private LayoutInflater inflater;

    public Lista_codigos(Context contexto){
        this.contexto = contexto;
        base = new BDCodigos(contexto,"Codigos.db",null,1);
    }

    @Override
    public int getCount() {
        return base.filas();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista = inflater.inflate(R.layout.lista_codigos, parent, false);

        TextView codigo = (TextView)vista.findViewById(R.id.Codigo);
        TextView fecha = (TextView)vista.findViewById(R.id.Fecha);
        ImageView img = (ImageView)vista.findViewById(R.id.Imagen);

        codigo.setText(base.Codigo(position,0));
        fecha.setText(base.Codigo(position,2));
        if(base.Imagen(position) == 1) {
            img.setImageResource(R.drawable.paloma);
        }else{
            img.setImageResource(R.drawable.tache);
        }

        return vista;
    }
}
