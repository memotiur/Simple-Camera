package com.simplemobiletools.camera;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.about_copyright) TextView copyright;
    @BindView(R.id.about_version) TextView version;
    @BindView(R.id.about_email) TextView emailTV;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        res = getResources();

        setupEmail();
        setupVersion();
        setupCopyright();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                final Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupEmail() {
        final String email = res.getString(R.string.email);
        final String appName = res.getString(R.string.app_name);
        final String href = "<a href=\"mailto:" + email + "?subject=" + appName + "\">" + email + "</a>";
        emailTV.setText(Html.fromHtml(href));
        emailTV.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setupVersion() {
        final String versionName = BuildConfig.VERSION_NAME;
        final String versionText = String.format(res.getString(R.string.version), versionName);
        version.setText(versionText);
    }

    private void setupCopyright() {
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        final String copyrightText = String.format(res.getString(R.string.copyright), year);
        copyright.setText(copyrightText);
    }

    @OnClick(R.id.about_license)
    public void licenseClicked() {
        final Intent intent = new Intent(getApplicationContext(), LicenseActivity.class);
        startActivity(intent);
    }
}
