package com.example.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class   MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LibraryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<LibraryItems> libraryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSampleLibrary();
        buildRecyclerView();

    }//end onCreate


    private void createSampleLibrary(){

        String summary = "Adaptation of the first of J.K. Rowling's popular children's novels about Harry Potter, a boy who learns on his eleventh birthday that he is the orphaned son of two powerful wizards and possesses unique magical powers of his own. He is summoned from his life as an unwanted child to become a student at Hogwarts, an English boarding school for wizards. There, he meets several friends who become his closest allies and help him discover the truth about his parents' mysterious deaths.";

        libraryList = new ArrayList<>();
        for(int i = 0; i <8; i ++){
            libraryList.add(new LibraryItems(R.drawable.harry_potter,"J.K Rowling","Harry Potter",
                    5,summary,"05/12/2010"));
            libraryList.add(new LibraryItems(R.drawable.percy_jackson,"Rick Riordan","Percy Jackson",
                    10,summary,"02/20/2011"));
            libraryList.add(new LibraryItems(R.drawable.onepiece,"Eichiro Oda", "One Piece",100,
                    summary,"01/01/1999"));
        }



    }//end createSampleList

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.mainRecyclerView);
        mRecyclerView.setHasFixedSize(true);
       // mAdapter = new LibraryAdapter(libraryList);
        mAdapter = new LibraryAdapter(libraryList);
        mRecyclerView.setAdapter(mAdapter);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mLayoutManager = new GridLayoutManager(this,2);
        }else{
            mLayoutManager = new GridLayoutManager(this,4);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);

        //for libraryAdapter

        mAdapter.setOnItemClickListener(new LibraryAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                Intent intent = new Intent(MainActivity.this,LibraryInfo.class);
                intent.putExtra("Library item",libraryList.get(position));
                startActivity(intent);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate our own menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.library_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)searchItem.getActionView();
        //changes the search icon on the keyboard to a default icon
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });


        return true;
    }

}//end class
