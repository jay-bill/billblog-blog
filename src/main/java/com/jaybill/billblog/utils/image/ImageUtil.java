package com.jaybill.billblog.utils.image;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
@Component
public class ImageUtil {
	
	@Autowired(required=false)
    private FastFileStorageClient storageClient;//fastdfs客户端
	
	public String uploadToFastDFS(String content){
		String arr [] = content.split(";base64,");
		//图片类型
		String imgType = arr[0].split("/")[1];
		//编好码的图片
		String byteStr = arr[1].split("\"")[0];
		byte [] buff = Base64.decodeBase64(byteStr);
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream,buff.length, imgType,null);
        return getResAccessUrl(storePath);
	}
	 // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl =  "http://192.168.241.134:8888/" + storePath.getFullPath();
        return fileUrl;
    }
	
	/**
	 * 对base64位编码的图片解码，并且保存到本地（或者fastdfs数据库中）;
	 * @param path 保存路径
	 * @param imgStr 图片编码后的字符串
	 * @return 返回-1：FileNotFoundException；返回-2：复制图片时出现异常；返回1：成功。
	 */
	public static int decodeBase64Image(String path,String imgStr){
		String arr [] = imgStr.split(";base64,");
		//图片类型
		String imgType = arr[0].split("/")[1];
		//编好码的图片
		String byteStr = arr[1].split("\"")[0];
		byte [] bt = Base64.decodeBase64(byteStr);
		FileOutputStream of = null;
		try {
			of = new FileOutputStream(new File(path+"."+imgType));
			of.write(bt, 0, bt.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -2;
		}finally{
			try {
				if(of!=null)
					of.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
}
