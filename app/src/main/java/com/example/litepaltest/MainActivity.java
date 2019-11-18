package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.nio.BufferUnderflowException;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase=(Button) findViewById(R.id.create_database); //创建数据库
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();

            }
        });
        Button addData=(Button) findViewById(R.id.add_data); //添加数据
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Da Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });
        Button updateData=(Button) findViewById(R.id.update_data);//更新数据
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setPrice(14.95);
                book.setToDefault("pages");
                book.setPress("Anchor");
                book.updateAll("name=? and author=?","The Lost Symbol","Dan Brown");
            }
        });
        Button deleteButton =(Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DataSupport.delete(Book.class,2);
                DataSupport.deleteAll(Book.class,"price < ?","15");
            }
        });
        Button queryBtton =(Button) findViewById(R.id.query_data);
        queryBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books=DataSupport.limit(1).offset(1).find(Book.class);
//                books=DataSupport.select("name","author","pages")
//                                    .where("pages > ?")
//                                    .order("pages")
//                                    .limit(10)
//                                    .offset(10)
//                                    .find(Book.class);
                for(Book book:books){
                    Log.d(TAG,"book name is "+book.getName());
                    Log.d(TAG,"book author is "+book.getAuthor());
                    Log.d(TAG,"book pages is "+book.getPages());
                    Log.d(TAG,"book price is "+book.getPrice());
                    Log.d(TAG,"book press is "+book.getPress());
                }
            }
        });
    }
}
