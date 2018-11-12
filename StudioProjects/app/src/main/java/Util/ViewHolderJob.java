package Util;

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderJob {
    TextView txvTitle;
    TextView txvJobAddress;
    ImageView imgvJobType;

    public TextView getTxvTitle() {
        return txvTitle;
    }

    public void setTxvTitle(TextView txvTitle) {
        this.txvTitle = txvTitle;
    }

    public TextView getTxvJobAddress() {
        return txvJobAddress;
    }

    public void setTxvJobAddress(TextView txvJobAddress) {
        this.txvJobAddress = txvJobAddress;
    }

    public ImageView getImgvJobType() {
        return imgvJobType;
    }

    public void setImgvJobType(ImageView imgvJobType) {
        this.imgvJobType = imgvJobType;
    }
}
