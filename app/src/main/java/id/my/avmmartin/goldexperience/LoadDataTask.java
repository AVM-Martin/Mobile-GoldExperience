package id.my.avmmartin.goldexperience;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

class LoadDataTask extends AsyncTask<Void, String, Void> {
    public interface Listener {
        void load_data();
    }

    private LoadDataTask.Listener listener;
    private ProgressDialog dialogue;

    LoadDataTask(Context context) {
        super();

        listener = (LoadDataTask.Listener)context;
    }

    @Override protected void onPreExecute() {
        super.onPreExecute();

        dialogue = new ProgressDialog((Context)listener);
        dialogue.setTitle("Loading items..");
        dialogue.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialogue.setIndeterminate(true);
        dialogue.show();
    }

    @Override protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        dialogue.dismiss();
    }

    @Override protected Void doInBackground(Void... params) {
        listener.load_data();
        return null;
    }
}
