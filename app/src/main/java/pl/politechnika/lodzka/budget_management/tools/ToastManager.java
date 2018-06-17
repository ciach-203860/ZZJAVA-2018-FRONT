package pl.politechnika.lodzka.budget_management.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by waver on 17.06.2018.
 */

public abstract class ToastManager {
    public static void ShowToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
