package com.example.examenfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examenfinal.modelo.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProductoActivity extends AppCompatActivity {
    private EditText edtNombre, edtPrecio, edtStock;
    private TextView txtTitulo;
    private Producto producto;
    private int idproduct;
    private String key = "";
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtTitulo = (TextView) findViewById(R.id.text_edit_product);
        setContentView(R.layout.activity_producto);
        edtNombre = (EditText) findViewById(R.id.txtNombre);
        edtPrecio = (EditText) findViewById(R.id.txtProduct);
        edtStock = (EditText) findViewById(R.id.txtStock);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        key  = getIntent().getStringExtra("PRODUCTO_KEY");
        setTitle("CREAR");
        if(key!=null ){
            databaseReference.child("Productos").child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    edtNombre.setText(snapshot.child("nombres").getValue().toString());
                    edtPrecio.setText(snapshot.child("precio").getValue().toString());
                    edtStock.setText(snapshot.child("stock").getValue().toString());
                    setTitle("ACTUALIZAR");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"xxx: "+key, Toast.LENGTH_SHORT).show();
        }
    }
    private void registrar(){
        boolean validar = true;
        String nombre = edtNombre.getText().toString();
        String precio = edtPrecio.getText().toString();
        String stock = edtStock.getText().toString();
        if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Login_validaNombre));
        }
        if(precio == null || precio.equals("")){
            validar = false;
            edtPrecio.setError(getString(R.string.Login_validaProducto));
        }
        if(stock == null || stock.equals("")){
            validar = false;
            edtStock.setError(getString(R.string.Login_validaProducto));
        }
        producto  = new Producto(nombre, precio, stock);
        if(validar && key==null) {

             databaseReference.child("Productos").push().setValue(producto).addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void aVoid) {
                     Toast.makeText(ProductoActivity.this,"Producto Registrado correctamente...",Toast.LENGTH_LONG).show();
                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Toast.makeText(ProductoActivity.this,"Error al guardar el Producto...",Toast.LENGTH_LONG).show();
                 }
             });
        }else{
            Map<String,Object> user = new HashMap<>();
            user.put("nombres",nombre);
            user.put("precio",precio);
            user.put("stock",stock);
            databaseReference.child("Productos").child(key).updateChildren(user);
        };

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_producto, menu);
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardar:
                this.registrar();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.action_menu_sair:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}