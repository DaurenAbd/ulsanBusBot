package com.gun0912.tedpermission;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.StringRes;
import com.gun0912.tedpermission.util.ObjectUtils;

public abstract class PermissionBuilder<T extends PermissionBuilder> {
    private Context context;
    private CharSequence deniedCloseButtonText;
    private CharSequence denyMessage;
    private CharSequence denyTitle;
    private boolean hasSettingBtn = true;
    private PermissionListener listener;
    private String[] permissions;
    private CharSequence rationaleConfirmText;
    private CharSequence rationaleMessage;
    private CharSequence rationaleTitle;
    private CharSequence settingButtonText;

    public PermissionBuilder(Context context) {
        this.context = context;
        this.deniedCloseButtonText = context.getString(C0249R.string.tedpermission_close);
        this.rationaleConfirmText = context.getString(C0249R.string.tedpermission_confirm);
    }

    protected void checkPermissions() {
        if (this.listener == null) {
            throw new IllegalArgumentException("You must setPermissionListener() on TedPermission");
        } else if (ObjectUtils.isEmpty(this.permissions)) {
            throw new IllegalArgumentException("You must setPermissions() on TedPermission");
        } else if (VERSION.SDK_INT < 23) {
            this.listener.onPermissionGranted();
        } else {
            Intent intent = new Intent(this.context, TedPermissionActivity.class);
            intent.putExtra(TedPermissionActivity.EXTRA_PERMISSIONS, this.permissions);
            intent.putExtra(TedPermissionActivity.EXTRA_RATIONALE_TITLE, this.rationaleTitle);
            intent.putExtra(TedPermissionActivity.EXTRA_RATIONALE_MESSAGE, this.rationaleMessage);
            intent.putExtra(TedPermissionActivity.EXTRA_DENY_TITLE, this.denyTitle);
            intent.putExtra(TedPermissionActivity.EXTRA_DENY_MESSAGE, this.denyMessage);
            intent.putExtra(TedPermissionActivity.EXTRA_PACKAGE_NAME, this.context.getPackageName());
            intent.putExtra(TedPermissionActivity.EXTRA_SETTING_BUTTON, this.hasSettingBtn);
            intent.putExtra(TedPermissionActivity.EXTRA_DENIED_DIALOG_CLOSE_TEXT, this.deniedCloseButtonText);
            intent.putExtra(TedPermissionActivity.EXTRA_RATIONALE_CONFIRM_TEXT, this.rationaleConfirmText);
            intent.putExtra(TedPermissionActivity.EXTRA_SETTING_BUTTON_TEXT, this.settingButtonText);
            intent.addFlags(268435456);
            TedPermissionActivity.startActivity(this.context, intent, this.listener);
        }
    }

    public T setPermissionListener(PermissionListener listener) {
        this.listener = listener;
        return this;
    }

    public T setPermissions(String... permissions) {
        this.permissions = permissions;
        return this;
    }

    public T setRationaleMessage(@StringRes int stringRes) {
        return setRationaleMessage(getText(stringRes));
    }

    private CharSequence getText(@StringRes int stringRes) {
        if (stringRes > 0) {
            return this.context.getText(stringRes);
        }
        throw new IllegalArgumentException("Invalid String resource id");
    }

    public T setRationaleMessage(CharSequence rationaleMessage) {
        this.rationaleMessage = rationaleMessage;
        return this;
    }

    public T setRationaleTitle(@StringRes int stringRes) {
        return setRationaleTitle(getText(stringRes));
    }

    public T setRationaleTitle(CharSequence rationaleMessage) {
        this.rationaleTitle = rationaleMessage;
        return this;
    }

    public T setDeniedMessage(@StringRes int stringRes) {
        return setDeniedMessage(getText(stringRes));
    }

    public T setDeniedMessage(CharSequence denyMessage) {
        this.denyMessage = denyMessage;
        return this;
    }

    public T setDeniedTitle(@StringRes int stringRes) {
        return setDeniedTitle(getText(stringRes));
    }

    public T setDeniedTitle(CharSequence denyTitle) {
        this.denyTitle = denyTitle;
        return this;
    }

    public T setGotoSettingButton(boolean hasSettingBtn) {
        this.hasSettingBtn = hasSettingBtn;
        return this;
    }

    public T setGotoSettingButtonText(@StringRes int stringRes) {
        return setGotoSettingButtonText(getText(stringRes));
    }

    public T setGotoSettingButtonText(CharSequence rationaleConfirmText) {
        this.settingButtonText = rationaleConfirmText;
        return this;
    }

    public T setRationaleConfirmText(@StringRes int stringRes) {
        return setRationaleConfirmText(getText(stringRes));
    }

    public T setRationaleConfirmText(CharSequence rationaleConfirmText) {
        this.rationaleConfirmText = rationaleConfirmText;
        return this;
    }

    public T setDeniedCloseButtonText(CharSequence deniedCloseButtonText) {
        this.deniedCloseButtonText = deniedCloseButtonText;
        return this;
    }

    public T setDeniedCloseButtonText(@StringRes int stringRes) {
        return setDeniedMessage(getText(stringRes));
    }
}
