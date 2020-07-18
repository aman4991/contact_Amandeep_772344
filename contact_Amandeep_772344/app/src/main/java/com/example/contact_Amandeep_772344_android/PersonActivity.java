package com.example.contact_Amandeep_772344_android;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {

    DatabaseHelperPerson mDataBase;
    List<Person> personListDetail;
    List<Person> searchListData;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personactivity);

        searchView = findViewById(R.id.searchbar);
        listView = findViewById(R.id.lvpersons);
        personListDetail = new ArrayList<>();
        mDataBase =   new DatabaseHelperPerson(this);
        searchListData = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        loadSaveData();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty()){
                    searchListData.clear();
                    for(int i = 0; i< personListDetail.size(); i++) {
                        Person contact = personListDetail.get(i);
                        if (contact.firstname.contains(newText)) {
                            searchListData.add(contact);
                        }
                    }
                    PersonAdapter pAdapter = new PersonAdapter(PersonActivity.this, R.layout.person_list, searchListData, mDataBase);
                    listView.setAdapter(pAdapter);
                }
                if(newText.isEmpty()) {
                    PersonAdapter pAdapter = new PersonAdapter(PersonActivity.this, R.layout.person_list, searchListData, mDataBase);
                    listView.setAdapter(pAdapter);
                }
                return  false;
            }
        });
    }

    private void loadSaveData() {
        Cursor cursor = mDataBase.getAllPerson();
        if(cursor.moveToFirst()){
            do {
                personListDetail.add(new Person(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));

            }while (cursor.moveToNext());
            cursor.close();
            //we use a custom adapter to show List
            PersonAdapter personAdapter = new PersonAdapter(this, R.layout.person_list, personListDetail, mDataBase);
           listView.setAdapter(personAdapter);
        }
    }
}