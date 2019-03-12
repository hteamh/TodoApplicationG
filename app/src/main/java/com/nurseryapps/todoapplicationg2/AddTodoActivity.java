package com.nurseryapps.todoapplicationg2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nurseryapps.todoapplicationg2.Base.BaseActivity;
import com.nurseryapps.todoapplicationg2.BataBase.Model.Todo;
import com.nurseryapps.todoapplicationg2.BataBase.MyDataBase;

public class AddTodoActivity extends BaseActivity implements View.OnClickListener {

    protected EditText title;
    protected EditText date;
    protected EditText content;
    protected Button add;
    public static  Todo todoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        initView();
        if(todoItem!=null){
            add.setText(R.string.update);
            title.setText(todoItem.getTitle());
            content.setText(todoItem.getContent());
            date.setText(todoItem.getDateTime());
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {
            if(todoItem==null){
            String sTitle = title.getText().toString();
            String sContent = content.getText().toString();
            String sDate = date.getText().toString();
            //todo: Validation
            Todo todoItem = new Todo(sTitle,sContent,sDate);

            MyDataBase.getInstance(this)
                    .todoDao()
                    .AddTodo(todoItem);
            showConfirmationMessage(R.string.success,
                    R.string.todo_added_successfuly,
                    R.string.ok,
                    new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                           dialog.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
            }else {
                String sTitle = title.getText().toString();
                String sContent = content.getText().toString();
                String sDate = date.getText().toString();
                todoItem.setTitle(sTitle);
                todoItem.setContent(sContent);
                todoItem.setDateTime(sDate);
                MyDataBase.getInstance(activity)
                        .todoDao()
                        .updateTodo(todoItem);
                showConfirmationMessage(R.string.success,
                        R.string.todo_item_updated,
                        R.string.ok,
                        new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                finish();
                            }
                        }).setCancelable(false);


            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        todoItem=null;

    }



    private void initView() {
        title = (EditText) findViewById(R.id.title);
        date = (EditText) findViewById(R.id.date);
        content = (EditText) findViewById(R.id.content);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(AddTodoActivity.this);
    }
}
