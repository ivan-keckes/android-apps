package hr.android.keckesProject.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private final String name;
    private final String description;
    private final int imgID;
    private final String link;

    public Data(String name, String description, int imgID, String link) {
        this.name = name;
        this.description = description;
        this.imgID = imgID;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImgID() {
        return imgID;
    }

    public String getLink() {
        return link;
    }


    protected Data(Parcel in) {
        name = in.readString();
        description = in.readString();
        imgID = in.readInt();
        link = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(imgID);
        dest.writeString(link);
    }
}
