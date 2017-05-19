package com.jaybill.billblog.utils.file;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

/**
 * 文件工具类
 * @author jaybill
 *
 */
@Component
public class FileUtil {
	@Autowired(required=false)
    private FastFileStorageClient storageClient;//fastdfs客户端
	
	/**
	 * 上传html文件
	 * @param bt
	 * @return
	 */
	public String upload(byte [] bt,String type){
		InputStream in = new ByteArrayInputStream(bt);
		StorePath storePath = storageClient.uploadFile(in, bt.length, type, null);
		return getResAccessUrl(storePath);
	}
	
	//封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = "http://192.168.241.134:8888/" + storePath.getFullPath();
        return fileUrl;
    }
}
