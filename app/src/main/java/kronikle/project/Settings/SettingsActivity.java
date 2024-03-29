package kronikle.project.Settings;

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
import kronikle.project.Profile.ProfileActivity;
import kronikle.project.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

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

    private ImageView iconSettings;
    private TextView textViewSettings;

    private ImageView iconSignOut;
    private TextView textViewSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        initializer();
        toolbarInitializer();
        drawerMenuInitializer();
    }

    private void initializer() {
        linearLayout = findViewById(R.id.linear_layout_SA);
        toolbar = findViewById(R.id.toolbar_SA);
        viewPager = findViewById(R.id.view_pager_SA);
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

        layoutHome.setOnClickListener(this);
        layoutActivity.setOnClickListener(this);
        layoutProfile.setOnClickListener(this);
        layoutContactUs.setOnClickListener(this);
        layoutInfo.setOnClickListener(this);
        layoutSettings.setOnClickListener(this);
        layoutSignOut.setOnClickListener(this);

        iconSettings = findViewById(R.id.icon_settings_DM);
        textViewSettings = findViewById(R.id.text_view_settings_DM);

        iconSettings.setImageResource(R.drawable.icon_settings_focused);
        textViewSettings.setTextColor(getResources().getColor(R.color.colorTextLight));

        iconSignOut = findViewById(R.id.icon_sign_out_DM);
        textViewSignOut = findViewById(R.id.text_view_sign_out_DM);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id. layout_home_DM:
                finish();
                break;

            case R.id.layout_activity_DM:
                Intent ActivityIntent = new Intent(getBaseContext(), ActivityActivity.class);
                startActivity(ActivityIntent);
                finish();
                break;

            case R.id.layout_profile_DM:
                Intent ProfileIntent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(ProfileIntent);
                finish();
                break;

            case R.id.layout_contact_us_DM:
                Intent ContactUsIntent = new Intent(getBaseContext(), ContactUsActivity.class);
                startActivity(ContactUsIntent);
                finish();
                break;

            case R.id.layout_info_DM:
                Intent InfoIntent = new Intent(getBaseContext(), InfoActivity.class);
                startActivity(InfoIntent);
                finish();
                break;

            case R.id.layout_settings_DM:
                slidingRootNav.closeMenu();
                break;

            case R.id.layout_sign_out_DM:
                iconSettings.setImageResource(R.drawable.icon_settings);
                textViewSettings.setTextColor(getResources().getColor(R.color.colorBaseLight));

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
                }, 230);
                break;
        }
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