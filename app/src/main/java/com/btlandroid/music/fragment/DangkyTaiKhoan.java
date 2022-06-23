package com.btlandroid.music.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.btlandroid.music.R;
import com.btlandroid.music.model.ResponseModel;
import com.btlandroid.music.model.User;
import com.btlandroid.music.retrofit.APIService;
import com.btlandroid.music.retrofit.DataService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangkyTaiKhoan extends AppCompatDialogFragment{

    TextInputLayout tk, mk, emaildangky;
    TextInputEditText maxndky;
    Button btnDangKy;
    ImageView imgclose;
    boolean accept = false, aceptmail = false;
    private ExampleDialogListener listener;
    String taikhoan="";
    String matkhau="";
    TextInputLayout email="";
    static int interval;
    static Timer timer;
    int delay = 1000, period = 1000, timeValue, code;
//    LoadingDialog loadingDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dang_ky, null);
        tk = view.findViewById(R.id.edttaikhoan);
        mk = view.findViewById(R.id.edtmatkhau);
//        loadingDialog = new LoadingDialog(getActivity());
        emaildangky = view.findViewById(R.id.emaildangky);
        btnDangKy = view.findViewById(R.id.btndangKy);
        imgclose = view.findViewById(R.id.imageClose);
        builder.setView(view);



        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept = false;
                try {
//                    loadingDialog.show();
                    taikhoan = tk.getEditText().getText().toString().trim();
                    matkhau = mk.getEditText().getText().toString().trim();
                }catch (Exception e){

                }
                if(taikhoan.trim().length() < 6 || taikhoan.trim().length() > 25){
                    Toast.makeText(getActivity(), "Độ dài tài khoản từ 6 -> 25 ký tự", Toast.LENGTH_LONG).show();
//                    loadingDialog.dismiss();
                }else {
                    checkUser(taikhoan);
                }
            }
        });
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }
    private static final int setInterval() {
        if (interval == 1){
            timer.cancel();
        }
        return --interval;
    }
    public void checkUser(String us) {
        DataService dataservice = APIService.getService();
        Call<String> callback = dataservice.user(tk, mk, email);
        callback.enqueue(new Callback<User>() {
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "Tài khoản đã được sử dụng", Toast.LENGTH_SHORT).show();
//                        loadingDialog.dismiss();
                    } else {
                        if (matkhau.trim().length() < 6 || matkhau.trim().length() > 36){
                            Toast.makeText(getActivity(), "Độ dài mật khẩu từ 6 -> 36 ký tự", Toast.LENGTH_LONG).show();
//                            loadingDialog.dismiss();
                        }else if (email.equals(emaildangky.getEditText().getText().toString().trim())){
                            if (maxacnhan.equals(String.valueOf(code))){
                                listener.apply(taikhoan, matkhau, email);
                            }else {
                                Toast.makeText(getActivity(), "Mã xác nhận không đúng", Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Email này chưa nhận mã", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismiss();
                        }
                    }
                }
            }

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }

        });
    }
    public void checkEmail(String em) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.checkemail(em);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "email đã được sử dụng", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();
                    } else {
                        senMail(email);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Gửi mã thất bại", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ExampleDialogListener) context;
    }

    @Override
    public void send(boolean check) {
        if (check){
            Toast.makeText(getActivity(), "Đã gửi", Toast.LENGTH_SHORT).show();
            String secs = "60";
            interval = Integer.parseInt(secs);
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    timeValue = setInterval();
                    if (timeValue == 0){
                        btngetmadangky.setText("Lấy mã");
                    }else {
                        btngetmadangky.setText(""+timeValue);
                    }
                }
            }, delay, period);
            loadingDialog.dismiss();
        }else {
            loadingDialog.dismiss();
            Toast.makeText(getActivity(), "Gửi email thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public interface ExampleDialogListener{
        void apply(String taikhoan, String matkhau, TextInputLayout email);
    }
}
