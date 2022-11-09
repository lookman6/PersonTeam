package com.tp_examen.projetcrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePersonneActivity extends AppCompatActivity {

    EditText edtNom, edtPrenom, edtAge;
    Button btnAjouter;
    PersonneDAO h = new PersonneDAO(CreatePersonneActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personne);

        edtNom = findViewById(R.id.edtNom);
        edtPrenom = findViewById(R.id.edtPrenom);
        edtAge = findViewById(R.id.edtAge);

        btnAjouter = (Button) findViewById(R.id.btnAjouter);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Personne p = new Personne(edtNom.getText().toString(),
                        edtPrenom.getText().toString(),
                        Integer.parseInt(edtAge.getText().toString()));

                h.insertPersonne(p);
                Intent intent = new Intent(CreatePersonneActivity.this,ListPersonneActivity.class);
                startActivity(intent);
            }
        });

    }
}
