package com.example.quanlythuvien;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.quanlythuvien.fragment.ChangePassFragment;
import com.example.quanlythuvien.fragment.DoanhThuFragment;
import com.example.quanlythuvien.fragment.HoaDonFragment;
import com.example.quanlythuvien.fragment.KhachHangFragment;
import com.example.quanlythuvien.fragment.LoaiSachFragment;
import com.example.quanlythuvien.fragment.NhanVienFragment;
import com.example.quanlythuvien.fragment.SachFragment;
import com.example.quanlythuvien.fragment.TopFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    MaterialToolbar toolbar;
    View mHeaderView;

    TextView edUser;
    private NavigationView nv;
    private BottomNavigationView bm;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        nv = findViewById(R.id.nvView);
//        bm = findViewById(R.id.btt_nav);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_tab_bar);
        ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager = getSupportFragmentManager();
        HoaDonFragment hoaDonFragment = new HoaDonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,hoaDonFragment)
                .commit();

        nv.setCheckedItem(R.id.nav_HoaDon);
//        bm.getMenu().findItem(R.id.navb_HoaDon).setChecked(true);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        edUser.setText("Welcome "+user+"!");

        if(user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.nav_sub_NhanVien).setVisible(true);

        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_HoaDon:
                        HoaDonGE();
                        nv.setCheckedItem(R.id.nav_HoaDon);
//                        bm.getMenu().findItem(R.id.navb_HoaDon).setChecked(true);
                        break;
                    case R.id.nav_LoaiSach:
                        LoaiSachGE();
                        nv.setCheckedItem(R.id.nav_LoaiSach);
//                        bm.getMenu().findItem(R.id.navb_LoaiSach).setChecked(true);
                        break;
                    case R.id.nav_Sach:
                        SachGE();
                        nv.setCheckedItem(R.id.nav_Sach);
//                        bm.getMenu().findItem(R.id.navb_Sach).setChecked(true);
                        break;
                    case R.id.nav_KhachHang:
                        KhachHangGE();
                        nv.setCheckedItem(R.id.nav_KhachHang);
                        break;
                    case R.id.nav_sub_Top:
                        TopGE();
                        nv.setCheckedItem(R.id.nav_sub_Top);
//                        bm.getMenu().findItem(R.id.navb_Top).setChecked(true);
                        break;
                    case R.id.nav_sub_DoanhThu:
                        DoanhThuGE();
                        nv.setCheckedItem(R.id.nav_sub_DoanhThu);
                        break;
                    case R.id.nav_sub_NhanVien:
                        NhanVienGE();
                        nv.setCheckedItem(R.id.nav_sub_NhanVien);
                        break;
                    case R.id.nav_sub_Pass:
                        ChangePassGE();
                        nv.setCheckedItem(R.id.nav_sub_Pass);
                        break;
                    case R.id.nav_sub_Logout:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle( Html.fromHtml("<font color='#ff7171'>Bạn muốn Đăng Xuất khỏi ứng dụng không?</font>"));
                        builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
//        bm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                FragmentManager manager = getSupportFragmentManager();
//                switch (item.getItemId()){
//                    case R.id.navb_Sach:
//                        GiayGE();
//                        nv.setCheckedItem(R.id.nav_Sach);
//                        break;
//                    case R.id.navb_LoaiSach:
//                        LoaiGiayGE();
//                        nv.setCheckedItem(R.id.nav_LoaiSach);
//                        break;
//                    case R.id.navb_HoaDon:
//                        HoaDonGE();
//                        nv.setCheckedItem(R.id.nav_HoaDon);
//                        break;
//                    case R.id.navb_Top:
//                        TopGE();
//                        nv.setCheckedItem(R.id.nav_sub_Top);
//                        break;
//                }
//                return true;
//            }
//        });

    }
    private void HoaDonGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("QUẢN LÝ HÓA ĐƠN");
        HoaDonFragment hoaDonFragment = new HoaDonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,hoaDonFragment)
                .commit();
    }
    private void SachGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("QUẢN LÝ SÁCH");
        SachFragment sachFragment = new SachFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, sachFragment)
                .commit();
    }
    private void LoaiSachGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("QUẢN LÝ LOẠI SÁCH");
        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,loaiSachFragment)
                .commit();
    }
    private void TopGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Top 5 SÁCH MUA NHIỀU NHẤT");
        TopFragment topFragment = new TopFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,topFragment)
                .commit();
    }
    private void DoanhThuGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("THỐNG KÊ DOANH THU");
        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,doanhThuFragment)
                .commit();
    }
    private void KhachHangGE() {
        FragmentManager manager = getSupportFragmentManager();
        setTitle("QUẢN LÝ KHÁCH HÀNG");
        KhachHangFragment khachHangFragment = new KhachHangFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, khachHangFragment)
                .commit();
    }
    private void NhanVienGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("QUẢN LÝ NHÂN VIÊN");
        NhanVienFragment nhanVienFragment = new NhanVienFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,nhanVienFragment)
                .commit();
    }
    private void ChangePassGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("THAY ĐỔI MẬT KHẨU");
        ChangePassFragment changePassFragment = new ChangePassFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,changePassFragment)
                .commit();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
    //Out Ứng Dụng
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "NHẤN 2 LẦN ĐỂ THOÁT", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
