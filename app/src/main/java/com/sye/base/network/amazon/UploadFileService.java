package com.sye.base.network.amazon;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.sye.base.util.Set;

import java.io.File;

public class UploadFileService extends AsyncTask {
    private OnS3ResponseListener listener;
    private Context APPLICATION_CONTEXT;
    private File fileToUpload;
    private String endpoint;
    private String message;


    public UploadFileService(Context context, File fileToUpload, String endpoint) {
        this.APPLICATION_CONTEXT = context;
        this.fileToUpload = fileToUpload;
        this.endpoint = endpoint;
    }

    public void setCallback(OnS3ResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        if (!fileToUpload.exists()) {
            listener.onS3Error();
            Log.d("doInBackground", "FILE DOESN'T EXIST");
            return null;
        }

        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                APPLICATION_CONTEXT,
                Set.POOL_ID,
                Regions.US_EAST_1 // Region
        );

        // Create an S3 client
        AmazonS3 s3 = new AmazonS3Client(credentialsProvider);

        TransferUtility transferUtility = new TransferUtility(s3, APPLICATION_CONTEXT);

        TransferObserver observer = transferUtility.upload(
                Set.BUCKET,     /* The bucket to upload to */
                endpoint,    /* The key for the uploaded object */
                fileToUpload,
                CannedAccessControlList.PublicReadWrite        /* Access public */
        );

        observer.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {

                if (state.toString().equals("COMPLETED")) {
                    listener.onS3Response();
                    message = state.toString();
                } else if (state.toString().equals("FAILED")) {
                    listener.onS3Error();
                    message = state.toString();
                }

                Log.d("statechange", state + " " + message);
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                //Display percentage transfered to user
                int progress = (int) ((bytesCurrent*100)/bytesTotal);
                message = (bytesCurrent == bytesTotal ? null : "");
                Log.i("progress", "-" + message);
            }

            @Override
            public void onError(int id, Exception ex) {
                message = ex.getMessage();
                Log.e("message", ex.getMessage() + " " + message);
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
    }

}
