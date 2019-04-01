package com.example.workersside;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;

public class BarcodeScanner extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;

    //ArrayList<Meal> barcodeCollectionMealArrayList;

    //FirebaseFirestore db;
    //ArrayList<Meal> collectionMealArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        surfaceView = (SurfaceView) findViewById(R.id.camerapreview);
        textView = (TextView) findViewById(R.id.textView);

        //loadDataFromFirebase();

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(getApplicationContext(), this.barcodeDetector).setRequestedPreviewSize(640,480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try
                {
                    cameraSource.start(holder);
                }catch(IOException e)
                {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size() != 0) {
                    textView.post(new Runnable() {
                        @Override
                        public void run() {

                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);


                            /*
                            MealsForCollection barcodeMeals = new MealsForCollection();
                            barcodeMeals.loadDataFromFirebase();
                            barcodeCollectionMealArrayList = barcodeMeals.getCollectionMealArrayList();

                            for(int i =0; i < collectionMealArrayList.size(); i++){
                                if (collectionMealArrayList.get(i).getUserName() == qrCodes.valueAt(0).displayValue) {
                                    textView.setText(collectionMealArrayList.get(i).getUserName() + collectionMealArrayList.get(i).getMain() + collectionMealArrayList.get(i).getSide() + collectionMealArrayList.get(i).getDrink());
                                }
                            }
                            */
                            textView.setText(qrCodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });

    }
/*
    public void loadDataFromFirebase() {

        if(collectionMealArrayList.size()>0)
            collectionMealArrayList.clear();
        db.collection("ReadyToCollect")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot querySnapshot: task.getResult()){
                            Meal meal = new Meal(
                                    querySnapshot.getId(),
                                    querySnapshot.getString("CustomerUniId"),
                                    querySnapshot.getString("Main"),
                                    querySnapshot.getString("Side"),
                                    querySnapshot.getString("Drink"));
                            collectionMealArrayList.add(meal);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w("--1--",e.getMessage());
                    }
                });

        for(int i =0; i < collectionMealArrayList.size(); i++){
            Log.d("Data", collectionMealArrayList.get(i).toString());
        }
    }
*/
}
