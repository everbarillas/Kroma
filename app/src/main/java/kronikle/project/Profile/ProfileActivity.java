package kronikle.project.Profile;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Objects;

import kronikle.project.ContactUs.ContactUsActivity;
import kronikle.project.Activity.ActivityActivity;
import kronikle.project.Info.InfoActivity;
import kronikle.project.Landing.LandingActivity;
import kronikle.project.R;
import kronikle.project.Settings.SettingsActivity;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Toolbar toolbar;
    private SlidingRootNav slidingRootNav;
    private ViewPager viewPager;

    private LinearLayout layoutHome;
    private LinearLayout layoutActivity;
    private LinearLayout layoutProfile;
    private LinearLayout layoutContactUs;
    private LinearLayout layoutInfo;
    private LinearLayout layoutSettings;
    private LinearLayout layoutSignOut;

    private ImageView iconProfile;
    private TextView textViewProfile;

    private ImageView iconSignOut;
    private TextView textViewSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        initializer();
        toolbarInitializer();
        drawerMenuInitializer();
        drawerMenuListener();
    }

    private void initializer() {
        linearLayout = findViewById(R.id.linear_layout_MAA);
        toolbar = findViewById(R.id.toolbar_MAA);
        viewPager = findViewById(R.id.view_pager_MAA);
    }

    private void toolbarInitializer() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void drawerMenuInitializer() {
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(true)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                slidingRootNav.closeMenu();
            }
        }, 100);

        layoutHome = findViewById(R.id.layout_home_DM);
        layoutActivity = findViewById(R.id.layout_activity_DM);
        layoutProfile = findViewById(R.id.layout_profile_DM);
        layoutContactUs = findViewById(R.id.layout_contact_us_DM);
        layoutInfo = findViewById(R.id.layout_info_DM);
        layoutSettings = findViewById(R.id.layout_settings_DM);
        layoutSignOut = findViewById(R.id.layout_sign_out_DM);

        iconProfile = findViewById(R.id.icon_profile_DM);
        textViewProfile = findViewById(R.id.text_view_profile_DM);

        iconProfile.setImageResource(R.drawable.icon_profile_focused);
        textViewProfile.setTextColor(getResources().getColor(R.color.colorTextLight));

        iconSignOut = findViewById(R.id.icon_sign_out_DM);
        textViewSignOut = findViewById(R.id.text_view_sign_out_DM);
    }

    private void drawerMenuListener() {
        layoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityIntent = new Intent(getBaseContext(), ActivityActivity.class);
                startActivity(ActivityIntent);
                finish();
            }
        });

        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingRootNav.closeMenu();
            }
        });

        layoutContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ContactUsIntent = new Intent(getBaseContext(), ContactUsActivity.class);
                startActivity(ContactUsIntent);
                finish();
            }
        });

        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent InfoIntent = new Intent(getBaseContext(), InfoActivity.class);
                startActivity(InfoIntent);
                finish();
            }
        });

        layoutSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SettingsIntent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(SettingsIntent);
                finish();
            }
        });

        layoutSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconProfile.setImageResource(R.drawable.icon_profile);
                textViewProfile.setTextColor(getResources().getColor(R.color.colorBaseLight));

                iconSignOut.setImageResource(R.drawable.icon_sign_out_focused);
                textViewSignOut.setTextColor(getResources().getColor(R.color.colorTextLight));

                slidingRootNav.closeMenu();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent LandingActivityIntent = new Intent(getBaseContext(), LandingActivity.class);
                        startActivity(LandingActivityIntent);
                        overridePendingTransition(R.anim.enter_in_down, R.anim.exit_out_down);
                        finishAffinity();
                    }
                }, 300);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (slidingRootNav.isMenuOpened()) {
            slidingRootNav.closeMenu();
        }

        else {
            super.onBackPressed();
            slidingRootNav.openMenu();
        }
    }
}