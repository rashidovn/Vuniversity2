package com.example.vuniversity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import classes.TestAdapter;
import classes.Utility;

public class AddStudentActivity extends MainActivity {
	Button buttonSubmitNew;
	EditText editTextName, editTextSurname, editTextEska;
	String groupId;

	public void onClickAdd(View view) {
		if (editTextName.getText().length() <= 0
				|| editTextSurname.getText().length() <= 0
				|| editTextEska.getText().length() <= 0) {
			Utility.ShowMessageBox(view.getContext(),
					"Fill all the fields please..");
			return;
		}

		String name = editTextName.getText().toString();
		String surname = editTextSurname.getText().toString();
		String eska = editTextEska.getText().toString();

		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();

		if (mDbHelper.AddStudent(name, surname, eska, groupId)) {
			Utility.ShowMessageBox(this, "added");
			finish();
		} else {
			Utility.ShowMessageBox(this, "OOPS try again!");
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_student);
		if (savedInstanceState == null) {
		    Bundle extras = getIntent().getExtras();
		    if(extras == null) {
		        groupId= null;
		    } else {
		        groupId= extras.getString("id");
		    }
		} else {
		    groupId= (String) savedInstanceState.getSerializable("id");
		}

		buttonSubmitNew = (Button) findViewById(R.id.buttonSubmitNewStudent);
		editTextName = (EditText) findViewById(R.id.editTextStudentName);
		editTextSurname = (EditText) findViewById(R.id.editTextStudentSurname);
		editTextEska = (EditText) findViewById(R.id.editTextStudentEska);

	}

}
