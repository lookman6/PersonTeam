package com.tp_examen.projetcrud;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListPersonneActivity  extends AppCompatActivity {

    ListView ls;
    PersonneDAO h = new PersonneDAO(ListPersonneActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_personne);

        ls = (ListView) findViewById(R.id.listPersonnes);

        Cursor c = h.getAllPersonne();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(ListPersonneActivity.this,R.layout.item,c,
                new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2),c.getColumnName(3)},
                new int[]{R.id.id,R.id.nom,R.id.prenom,R.id.age},1);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtview = view.findViewById(R.id.id);
                Intent intent = new Intent(ListPersonneActivity.this,DetailPersonneActivity.class);
                intent.putExtra("id",txtview.getText().toString());
                startActivity(intent);
            }
        });

//        Intent MyIntent = getIntent();
//        String Message = MyIntent.getStringExtra("Message");
//        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }
}
