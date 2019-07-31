package nredondo26.com.nodesistalar;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

public class MyAccessibilityService extends AccessibilityService {

    public AccessibilityNodeInfo nodeuno;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        nodeuno = event.getSource();
        buscarpanel(nodeuno);
    }

    @Override
    public void onInterrupt() {
        Log.e("SERVICIO:", "Servicio Interrumpido");
    }

    @Override
    public void onServiceConnected() {
        Log.e("SERVICIO:", "Servicio conectado");
    }

    private void buscarpanel(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
        List<AccessibilityNodeInfo> unvisited = new ArrayList<>();
        unvisited.add(nodeInfo);

        while (!unvisited.isEmpty()) {
            AccessibilityNodeInfo node = unvisited.remove(0);

            if (node == null) {
                continue;
            }

            if (verquepasa(node)) {
                return;
            }

            for (int i = 0; i < node.getChildCount(); i++) {
                unvisited.add(node.getChild(i));
            }
        }
    }

    @SuppressLint("NewApi")
    private boolean verquepasa(AccessibilityNodeInfo nodeInfo) {

        if ("android.widget.TextView".equals(nodeInfo.getClassName()) && ("com.whatsapp:id/conversation_contact_name".equals(nodeInfo.getViewIdResourceName()) || "com.whatsapp.w4b:id/conversation_contact_name".equals(nodeInfo.getViewIdResourceName()) )  && nodeInfo.getText() != null)
        {
            Log.e("Nombre Contacto: ", String.valueOf(nodeInfo.getText()));
            return true;
        }
        else{
            return false;
        }
    }


}
