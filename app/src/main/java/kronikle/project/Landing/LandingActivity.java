package kronikle.project.Landing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.Objects;

import kronikle.project.R;
import kronikle.project.Adapters.ViewPagerAdapter;

public class LandingActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean backButtonPressedTwice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);

        initializer();
        tabLayoutInitializer();
        layoutFocus();
    }

    private void  initializer() {
        linearLayout = findViewById(R.id.linear_layout_LA);
        tabLayout = findViewById(R.id.tab_layout_LA);
        viewPager = findViewById(R.id.view_pager_LA);
    }

    public void tabLayoutInitializer() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new SignInFragment(), getString(R.string.sign_in));
        adapter.AddFragment(new SignUpFragment(), getString(R.string.sign_up));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void layoutFocus() {
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethod != null;
                inputMethod.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (backButtonPressedTwice) {
            super.onBackPressed();
        }

        else {

            new StyleableToast
                    .Builder(getApplicationContext())
                    .text(getString(R.string.exit_app))
                    .textColor(getResources().getColor(R.color.colorTextLight))
                    .backgroundColor(getResources().getColor(R.color.colorBackground))
                    .iconStart(R.drawable.icon_warning)
                    .cornerRadius(2)
                    .length(6000)
                    .show();

            backButtonPressedTwice = true;
            new CountDownTimer(2000, 1000) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    backButtonPressedTwice = false;
                }
            }.start();
        }
    }
}