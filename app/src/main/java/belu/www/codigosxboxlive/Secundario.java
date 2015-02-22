package belu.www.codigosxboxlive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Luis on 24/12/2014.
 */
public class Secundario extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundario);
        if (savedInstanceState == null) {

            String boton = getIntent().getExtras().getString("letra");
            if(boton.equals("A")){
                getFragmentManager().beginTransaction().replace(R.id.container, new Frag_agregar(this)).commit();
            }else{
                getFragmentManager().beginTransaction().replace(R.id.container, new Frag_Vista(this)).commit();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(this,Primario.class));
    }
}
