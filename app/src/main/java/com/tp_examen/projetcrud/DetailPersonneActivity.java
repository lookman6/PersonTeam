package com.tp_examen.projetcrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DetailPersonneActivity extends AppCompatActivity {

    EditText edtNom, edtPrenom, edtAge;
    Button btnModifier, btnSupprimer;
    String id;
    PersonneDAO h = new PersonneDAO(DetailPersonneActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_personne);

        edtNom = findViewById(R.id.nomDetail);
        edtPrenom = findViewById(R.id.prenomDetail);
        edtAge = findViewById(R.id.ageDetail);

        btnModifier = (Button) findViewById(R.id.btnModifier);
        btnSupprimer = (Button) findViewById(R.id.btnSupprimer);

        id = getIntent().getStringExtra("id");
        Personne p = h.getOnePersonne(Integer.parseInt(id));

//        System.out.println(Integer.parseInt(id));

        edtNom.setText(p.getNom());
        edtPrenom.setText(p.getPrenom());
        edtAge.setText(String.valueOf(p.getAge()));

        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personne p = new Personne(Integer.parseInt(id),edtNom.getText().toString(),
                        edtPrenom.getText().toString(),Integer.parseInt(edtAge.getText().toString()));
                h.updatePersonne(p);
                Intent intent = new Intent(DetailPersonneActivity.this,ListPersonneActivity.class);
//                intent.putExtra("Message", "Modification effectuée");
                startActivity(intent);
            }
        });

        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h.deletePersonne(Integer.parseInt(id));
                Intent intent = new Intent(DetailPersonneActivity.this,ListPersonneActivity.class);
                startActivity(intent);
            }
        });
    }
}
