package Model;

import android.text.format.DateFormat;

import com.orm.dsl.Table;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Table
public class Interview {
    Date date;
    Long job_id;
    public Interview() {
    }

    @Override
    public String toString()
    {
        if(date!=null) {
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTime(date);
            String dateString = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
            return dateString;
        }
        else
            return "(No date found)";
    }
    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
