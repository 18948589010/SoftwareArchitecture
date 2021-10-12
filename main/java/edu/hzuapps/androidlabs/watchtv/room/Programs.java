package edu.hzuapps.androidlabs.watchtv.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Programs implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String addr;

    @Ignore
    public Programs() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Programs(int id, String name, String addr) {
        this.id = id;
        this.name = name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Programs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(addr);
    }

    public static final Parcelable.Creator<Programs> CREATOR = new Creator(){

        @Override
        public Programs createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            Programs p = new Programs();
            p.setId(source.readInt());
            p.setName(source.readString());
            p.setAddr(source.readString());
            return p;
        }

        @Override
        public Programs[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Programs[size];
        }
    };
}
