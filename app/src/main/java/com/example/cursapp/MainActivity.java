package com.example.cursapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cursapp.adapter.CategoryAdapter;
import com.example.cursapp.adapter.CourseAdapter;
import com.example.cursapp.model.Category;
import com.example.cursapp.model.Cours;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView categoryRecycler, courseRecycle;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Cours> coursList = new ArrayList<>();
    static List<Cours> fullcoursList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Игры"));
        categoryList.add(new Category(2,"Сайты"));
        categoryList.add(new Category(3,"Языки"));
        categoryList.add(new Category(4,"Прочее"));
        categoryList.add(new Category(5,"Курсы"));

        setCategoryRecycler(categoryList);


        coursList.add(new Cours(1,"java","Профессия Java\nразработчик","1 января","Начальный","#424345","TextTest",3));
        coursList.add(new Cours(2,"python","Профессия Python\nразработчик","10 января","Начальный","#9FA52D","textTest",1));

        fullcoursList.addAll(coursList);

        setCourseRecycle(coursList);

        showAllCourses();



    }


    private void setCourseRecycle(List<Cours> coursList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        courseRecycle = findViewById(R.id.coursesRecycler);
        courseRecycle.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this,coursList);
        courseRecycle.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);


    }

    public static void showCoursesByCategory(int category){

        coursList.clear();
        coursList.addAll(fullcoursList);

        List<Cours> filterCourses = new ArrayList<>();

        for (Cours c: coursList){
            if (c.getCategory()==category)
                filterCourses.add(c);
        }

        coursList.clear();
        coursList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();//берет новое текущее значение и обновляет весь ресайкл вью.
    }
    public void showAllCourses(){
        ImageView allcurses = findViewById(R.id.allcourses);
        allcurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coursList.clear();
                coursList.addAll(fullcoursList);
                courseAdapter.notifyDataSetChanged();
            }
        });
    }
    public void openCard(View view){
        Intent intent = new Intent(this,OrderPage.class);
        startActivity(intent);
    }
}