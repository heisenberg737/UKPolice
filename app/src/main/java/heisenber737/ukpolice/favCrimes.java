package heisenber737.ukpolice;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class favCrimes {

       favCrimesHelper helper;

       public favCrimes(Context context)
       {
           helper=new favCrimesHelper(context);
       }
    public long insertData(String category,String month,String location_type,String outcome)
    {   SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(favCrimesHelper.CATEGORY,category);
        contentValues.put(favCrimesHelper.MONTH,month);
        contentValues.put(favCrimesHelper.LOCATION_TYPE,location_type);
        contentValues.put(favCrimesHelper.OUTCOME,outcome);
        long id=db.insert(favCrimesHelper.tableName,null,contentValues);
        return id;
    }



    public static class favCrimesHelper extends SQLiteOpenHelper{

        private static final String databaseName="FAVOURITESDATABASE";
        public static final String tableName="FAVOURITESTABLE";
        private static final int databaseVersion=6;
        public static final String CATEGORY="CATEGORY";
        public static final String LOCATION_TYPE="LOCATION_TYPE";
        public static final String OUTCOME="OUTCOME";
        public static final String ID="_ID";
        public static final String MONTH="MONTH";
        //create table command. added a new column called month. the code worked perfectly before inserting new column but since then there's problem.
        //i'm getting an error saying theres no column named MONTH in the table.
        private static final String query="CREATE TABLE "+tableName+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CATEGORY+" VARCHAR(255),"+MONTH+" VARCHAR(255),"+LOCATION_TYPE+" VARCHAR(255),"+OUTCOME+" VARCHAR(255));";
        Context context;

        public favCrimesHelper(Context context)
        {
            super(context,databaseName,null,databaseVersion);
            this.context=context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(query);

            }catch (SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(context,""+e,Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query="DROP TABLE IF EXISTS "+tableName+"";
            db.execSQL(query);
            onCreate(db);

        }



    }


}
