package demo.alenx.org.mydialog;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private MainFrameTask mainFrameTask = null;
    private MyDialog myDialog = null;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);



        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFrameTask = new MainFrameTask(MainActivity.this);
                mainFrameTask.execute();
            }
        });
    }


    @Override
    protected void onDestroy() {

        stopDialog();
        if (mainFrameTask!=null&&!mainFrameTask.isCancelled()){
            mainFrameTask.cancel(true);
        }
        super.onDestroy();

    }

    private void startDialog(){
        if (myDialog==null){
            myDialog = MyDialog.createDialog(this);
            myDialog.setMessage("加载中...");
        }
        myDialog.show();
    }

    private void stopDialog(){
        if (myDialog!=null){
            myDialog.dismiss();
        }
    }


    public class MainFrameTask extends AsyncTask<Integer,String,Integer>{

        private MainActivity mainActivity=null;

        public MainFrameTask(MainActivity mainActivity){
            this.mainActivity = mainActivity;
        }

        @Override
        protected void onCancelled() {
            stopDialog();
            super.onCancelled();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
           try{
               Thread.sleep(3000);
           }catch (InterruptedException e){
               e.printStackTrace();
           }
            return null;
        }

        @Override
        protected void onPreExecute() {
            startDialog();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            stopDialog();
            super.onPostExecute(integer);
        }
    }

}
