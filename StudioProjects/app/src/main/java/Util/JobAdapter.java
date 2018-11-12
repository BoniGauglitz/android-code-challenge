package Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alemoreirac.empregoligado.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Job;


public class JobAdapter extends ArrayAdapter<Job> implements View.OnClickListener
{

    Context mContext;

    public JobAdapter(List<Job> data, Context context)
    {
        super(context, R.layout.row_job,data);
//        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v)
    {
    }
//
//    @Override
//    public ColisaoTransito getItem(int position)
//    {
//        return
//    }

    public View getView(int position,View convertView,ViewGroup parent)
    {
        Job job = getItem(position);
        ViewHolderJob viewHolder;

        if(convertView == null && job != null)
        {
            viewHolder = new ViewHolderJob();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_job,parent,false);

            viewHolder.setTxvJobAddress((TextView) convertView.findViewById(R.id.txv_Job_Address_Row));
            viewHolder.setTxvTitle((TextView) convertView.findViewById(R.id.txv_Job_Title_Row));
            viewHolder.setImgvJobType((ImageView) convertView.findViewById(R.id.imgv_Job_Type_row));
//            viewHolder.setImgvAudio((ImageView) convertView.findViewById(R.id.imgv_row_AudioColisao));
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderJob) convertView.getTag();
        }

        viewHolder.getTxvTitle().setText(StringUtil.checkValue(job.getTitle(),40,"(Sem descrição)"));
        viewHolder.getTxvJobAddress().setText(StringUtil.checkValue(job.getAddressString(),45,"(Sem descrição)"));

        // Poderia ser feito um switch com o enum de job_type para ilustrar melhor o tipo de emprego
        Picasso.get().load(R.drawable.briefcase).fit().into(viewHolder.getImgvJobType());

        return convertView;

    }
}