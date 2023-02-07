package com.example.freetime.presenter;

import com.example.freetime.model.StudentModel;
import com.example.freetime.model.interfaces.IStudentModel;
import com.example.freetime.model.interfaces.IUserManageModel;
import com.example.freetime.view.IStudentView;

import java.util.Map;

public class StudentPresenter extends BasePresenter<IStudentView>{

    IStudentModel iStudentModel = new StudentModel();


}
