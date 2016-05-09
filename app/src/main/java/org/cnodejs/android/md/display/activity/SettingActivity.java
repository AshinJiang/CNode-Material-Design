package org.cnodejs.android.md.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.cnodejs.android.md.R;
import org.cnodejs.android.md.display.base.StatusBarActivity;
import org.cnodejs.android.md.display.listener.NavigationFinishClickListener;
import org.cnodejs.android.md.display.util.ThemeUtils;
import org.cnodejs.android.md.model.storage.SettingShared;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends StatusBarActivity {

    @BindView(R.id.setting_toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.setting_switch_notification)
    protected SwitchCompat switchNotification;

    @BindView(R.id.setting_switch_theme_dark)
    protected SwitchCompat switchThemeDark;

    @BindView(R.id.setting_switch_new_topic_draft)
    protected SwitchCompat switchNewTopicDraft;

    @BindView(R.id.setting_switch_topic_sign)
    protected SwitchCompat switchTopicSign;

    @BindView(R.id.setting_btn_modify_topic_sign)
    protected TextView btnModifyTopicSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppThemeLight, R.style.AppThemeDark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        switchNotification.setChecked(SettingShared.isEnableNotification(this));
        switchThemeDark.setChecked(SettingShared.isEnableThemeDark(this));
        switchNewTopicDraft.setChecked(SettingShared.isEnableNewTopicDraft(this));
        switchTopicSign.setChecked(SettingShared.isEnableTopicSign(this));
        btnModifyTopicSign.setEnabled(SettingShared.isEnableTopicSign(this));
    }

    @OnClick(R.id.setting_btn_notification)
    protected void onBtnNotificationClick() {
        switchNotification.toggle();
        SettingShared.setEnableNotification(this, switchNotification.isChecked());
    }

    @OnClick(R.id.setting_btn_theme_dark)
    protected void onBtnThemeDarkClick() {
        switchThemeDark.toggle();
        SettingShared.setEnableThemeDark(this, switchThemeDark.isChecked());
        ThemeUtils.notifyThemeApply(this, false);
    }

    @OnClick(R.id.setting_btn_new_topic_draft)
    protected void onBtnNewTopicDraftClick() {
        switchNewTopicDraft.toggle();
        SettingShared.setEnableNewTopicDraft(this, switchNewTopicDraft.isChecked());
    }

    @OnClick(R.id.setting_btn_topic_sign)
    protected void onBtnTopicSignClick() {
        switchTopicSign.toggle();
        SettingShared.setEnableTopicSign(this, switchTopicSign.isChecked());
        btnModifyTopicSign.setEnabled(switchTopicSign.isChecked());
    }

    @OnClick(R.id.setting_btn_modify_topic_sign)
    protected void onBtnModifyTopicSignClick() {
        startActivity(new Intent(this, ModifyTopicSignActivity.class));
    }

}
