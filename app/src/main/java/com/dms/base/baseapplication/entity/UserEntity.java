package com.dms.base.baseapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class UserEntity implements Parcelable {
    private String uid;

    private String token;

    private UserInfo userInfo;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.token);
        dest.writeParcelable(this.userInfo, flags);
    }

    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        this.uid = in.readString();
        this.token = in.readString();
        this.userInfo = in.readParcelable(UserInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };

    public static class UserInfo implements Parcelable {
        private String name;
        private int sex;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeInt(this.sex);
            dest.writeInt(this.age);
        }

        public UserInfo() {
        }

        protected UserInfo(Parcel in) {
            this.name = in.readString();
            this.sex = in.readInt();
            this.age = in.readInt();
        }

        public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
            @Override
            public UserInfo createFromParcel(Parcel source) {
                return new UserInfo(source);
            }

            @Override
            public UserInfo[] newArray(int size) {
                return new UserInfo[size];
            }
        };
    }
}
