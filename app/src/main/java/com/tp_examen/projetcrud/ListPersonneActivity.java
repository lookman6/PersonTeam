package com.tp_examen.projetcrud;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListPersonneActivity  extends AppCompatActivity {

    ListView ls;
    private Button btnSearch;
    private Button btnAll;
    private EditText res;
    private String resString;

    PersonneDAO h = new PersonneDAO(ListPersonneActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_personne);



        ls = (ListView) findViewById(R.id.listPersonnes);
        freshy("");









        //Cursor c = h.getAllPersonne();

       // btnAll.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //     public void onClick(View view) {
        //         SimpleCursorAdapter adapter = new SimpleCursorAdapter(ListPersonneActivity.this,R.layout.item,c,
        //                 new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2),c.getColumnName(3)},
        //                new int[]{R.id.id,R.id.nom,R.id.prenom,R.id.age},1);
        //        ls.setAdapter(adapter);
        // //    }
        // });

        // btnSearch.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //    public void onClick(View view) {

        //       Cursor cor = h.getSearch(resString);
        //       if(cor.getCount()==0)
        //        {
        //            Toast.makeText(ListPersonneActivity.this, "Bases de données vides",Toast.LENGTH_LONG).show();
        //       }
        //        else
        //        {
        //           SimpleCursorAdapter adapter = new SimpleCursorAdapter(ListPersonneActivity.this,R.layout.item,cor,
        //                    new String[]{cor.getColumnName(0),cor.getColumnName(1),cor.getColumnName(2),cor.getColumnName(3)},
        //                    new int[]{R.id.id,R.id.nom,R.id.prenom,R.id.age},1);
        //            ls.setAdapter(adapter);
        //        }

        //    }
        // });




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

    public void freshy(String res)
    {
        ArrayList<Personne> lis = h.getSearch2(res);
        DisplayListPerson disp = new DisplayListPerson(lis);
        ls.setAdapter(disp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.choice,menu);
        MenuItem mitem = menu.findItem(R.id.app_bar_search);
        SearchView sear = (SearchView) mitem.getActionView();
        sear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                freshy(s);
                return false;
            }
        });
        return true;
    }

    class DisplayListPerson extends BaseAdapter
    {
        private ArrayList<Personne> lst = new ArrayList<>();
        public DisplayListPerson(ArrayList<Personne> p)
        {
            this.lst=p;
        }
        @Override
        public int getCount()
        {
            return lst.size();
        }

        @Override
        public Object getItem(int position)
        {
            return lst.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return lst.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View v = getLayoutInflater().inflate(R.layout.item,null);
            TextView txtId = v.findViewById(R.id.id);
            TextView txtNom = v.findViewById(R.id.nom);
            TextView txtPrenom = v.findViewById(R.id.prenom);
            TextView txtAge = v.findViewById(R.id.age);
            txtId.setText(Integer.toString(lst.get(position).getId()));
            txtNom.setText(lst.get(position).getNom());
            txtPrenom.setText(lst.get(position).getPrenom());
            txtAge.setText(Integer.toString(lst.get(position).getAge()));
            return v;
        }
    }


}
