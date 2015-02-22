package belu.www.codigosxboxlive;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis on 19/12/2014.
 */

public class BDCodigos extends SQLiteOpenHelper {

    public BDCodigos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE IF NOT EXISTS CodXBOX (Codigo TEXT NOT NULL, Imagen INTEGER NOT NULL, Fecha TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertar (String codigo, int imagen, String Fecha){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO CodXBOX VALUES('"+ codigo +"', "+ imagen +",'"+ Fecha +"')");
        db.close();
    }

    public void modificar(String codigo){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE CodXBOX SET Imagen = 2 WHERE Codigo = '"+ codigo +"'");
        db.close();
    }

    public void eliminar(String codigo){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM CodXBOX WHERE Codigo = '"+ codigo +"'");
        db.close();
    }

    public int filas (){
        SQLiteDatabase db = getReadableDatabase();
        int filas = db.rawQuery("Select * from CodXBOX", null).getCount();
        db.close();
        return filas;
    }

    public int CodigosDisponibles(){
        SQLiteDatabase db = getReadableDatabase();
        int filas = db.rawQuery("Select * from CodXBOX WHERE Imagen = 1", null).getCount();
        db.close();
        return filas;
    }

    public String Codigo(int position, int columna){
        SQLiteDatabase db = getReadableDatabase();
        Cursor paquete = db.rawQuery("Select * from CodXBOX", null);
        paquete.moveToPosition(position);
        String dato = paquete.getString(columna);
        db.close();
        return dato;
    }

    public int Imagen(int position){
        SQLiteDatabase db = getReadableDatabase();
        Cursor paquete = db.rawQuery("Select Imagen from CodXBOX", null);
        paquete.moveToPosition(position);
        int dato = paquete.getInt(0);
        db.close();
        return dato;
    }

}
