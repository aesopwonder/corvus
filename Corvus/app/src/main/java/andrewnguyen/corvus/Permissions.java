package andrewnguyen.corvus;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;
import android.view.View;

/**
 * Created by andrewnguyen on 11/11/16.
 */
public abstract class Permissions extends Activity{
    private SparseIntArray mErrorString;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mErrorString = new SparseIntArray();
    }
    public abstract void onPermissionsGranted(int requestCode);

    public void requestAppPermissions(final String[]requestedPermissions, final int stringId, final int requestCode){
        mErrorString.put(requestCode, stringId);

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean showRequestPermissions = false;
        for(String permission: requestedPermissions){
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            showRequestPermissions = showRequestPermissions || ActivityCompat.shouldShowRequestPermissionRationale(this,permission);

        }
        if(permissionCheck!=PackageManager.PERMISSION_GRANTED){
            if(showRequestPermissions){
                Snackbar.make(findViewById(android.R.id.content), stringId, Snackbar.LENGTH_INDEFINITE).setAction("GRANT", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityCompat.requestPermissions(Permissions.this, requestedPermissions, requestCode);
                    }
                }).show();
            }
            else{
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }

        }
        else{
            onPermissionsGranted(requestCode);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for(int permission : grantResults){
            permissionCheck = permissionCheck + permission;
        }
        if((grantResults.length>0)&&PackageManager.PERMISSION_GRANTED==permissionCheck){
            onPermissionsGranted(requestCode);
        }
        else{
            //Display Message when contain some Dangerous Permission not accept
            System.out.println("Not Granted");
        }
    }
}

