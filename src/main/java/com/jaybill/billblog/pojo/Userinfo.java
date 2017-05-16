package com.jaybill.billblog.pojo;

import java.io.Serializable;

public class Userinfo implements Serializable{
	
	private static final long serialVersionUID = -628088343022781787L;
	
    private Long userId;

    private Byte userSex;

    private String userBirthday;  

    private String userSchool; //毕业学校

    private String userPosition;  //工作

    private Byte userLovestate;   //恋爱状态，0表示单身，1表示恋爱中，2表示已婚，3表示离异

    private String userSignature;  //个性签名

    private String userAddress;	  //住址
    
    private byte userTropism; //取向，0表示异性恋，1表示双性恋，2表示同性恋
    
    
    public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public byte getUserTropism() {
		return userTropism;
	}

	public void setUserTropism(byte userTropism) {
		this.userTropism = userTropism;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool == null ? null : userSchool.trim();
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition == null ? null : userPosition.trim();
    }

    public Byte getUserLovestate() {
        return userLovestate;
    }

    public void setUserLovestate(Byte userLovestate) {
        this.userLovestate = userLovestate;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature == null ? null : userSignature.trim();
    }
}